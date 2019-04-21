package com.interest.message.service.impl;

import com.interest.common.feign.InterestBbsFeign;
import com.interest.common.feign.InterestBlogFeign;
import com.interest.common.feign.InterestUserFeign;
import com.interest.common.model.PageResult;
import com.interest.common.model.Request.MsgRecodeRequest;
import com.interest.common.model.response.MsgContentVO;
import com.interest.common.model.response.UserHeadInfoVO;
import com.interest.common.utils.SecurityAuthenUtil;
import com.interest.message.dao.MsgRecordsDao;
import com.interest.message.model.entity.MsgRecordEntity;
import com.interest.message.model.response.MsgRecordVO;
import com.interest.message.service.MsgRecordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MsgRecordsServiceImpl implements MsgRecordsService {

    @Autowired
    private MsgRecordsDao msgRecordsDao;

    @Autowired
    private InterestBbsFeign interestBbsFeign;

    @Autowired
    private InterestBlogFeign interestBlogFeign;

    @Autowired
    private InterestUserFeign interestUserFeign;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public int getUnreadMsgCount(Integer userId) {
        return msgRecordsDao.getUnreadMsgCount(userId);
    }

    @Override
    public void insertMessage(MsgRecodeRequest msgRecodeRequest) {
        threadPoolTaskExecutor.execute(() -> {
            MsgRecordEntity msgRecordEntity = new MsgRecordEntity();
            BeanUtils.copyProperties(msgRecodeRequest, msgRecordEntity);
            log.info("insert | msg_records | data : {}", msgRecordEntity.toString());
            msgRecordsDao.addMsg(msgRecordEntity);
        });
    }

    @Override
    public PageResult<List<MsgRecordVO>> getUserMegsResult(int pageSize, int start) {
        int userId = SecurityAuthenUtil.getId();
        List<MsgRecordVO> msgRecordVOList = msgRecordsDao.getMsgListByUserId(userId, pageSize, start);

        // 获取回帖消息内容
        List<MsgContentVO> replyCardContentList = interestBbsFeign.getMsgContentByIds(msgRecordVOList.stream().map(MsgRecordVO::getReplyCardId).collect(Collectors.toSet())).getData();
        msgRecordVOList.forEach(msgRecordVO -> {
            if (msgRecordVO.getForm() == 0) {
                replyCardContentList.forEach(msgContentVO -> {
                    if (msgRecordVO.getReplyCardId() != null && msgRecordVO.getReplyCardId().intValue() == msgContentVO.getId().intValue()) {
                        msgRecordVO.setTitle(msgContentVO.getTitle());
                        msgRecordVO.setReplyContent(msgContentVO.getReplyContent());
                        msgRecordVO.setToId(msgContentVO.getToId());
                        msgRecordVO.setReplyUserId(msgContentVO.getReplyUserId());
                    }
                });
            }
        });
        // 获取文章回复消息内容
        List<MsgContentVO> commentContentList = interestBlogFeign.getMsgContentByIds(msgRecordVOList.stream().map(MsgRecordVO::getCommentId).collect(Collectors.toSet())).getData();
        msgRecordVOList.forEach(msgRecordVO -> {
            if (msgRecordVO.getForm() == 1) {
                commentContentList.forEach(msgContentVO -> {
                    if (msgRecordVO.getCommentId() != null && msgRecordVO.getCommentId().intValue() == msgContentVO.getId().intValue()) {
                        msgRecordVO.setTitle(msgContentVO.getTitle());
                        msgRecordVO.setReplyContent(msgContentVO.getReplyContent());
                        msgRecordVO.setToId(msgContentVO.getToId());
                        msgRecordVO.setReplyUserId(msgContentVO.getReplyUserId());
                    }
                });
            }
        });
        // 回去发送消息人信息
        List<UserHeadInfoVO> userHeadInfoVOList = interestUserFeign.getUsersHeadInfoByIds(msgRecordVOList.stream().map(MsgRecordVO::getReplyUserId).collect(Collectors.toSet())).getData();
        msgRecordVOList.forEach(msgRecordVO -> {
            userHeadInfoVOList.forEach(userHeadInfoVO -> {
                if (msgRecordVO.getReplyUserId() != null && msgRecordVO.getReplyUserId().intValue() == userHeadInfoVO.getUserId().intValue()) {
                    msgRecordVO.setReplyUserName(userHeadInfoVO.getUserName());
                    msgRecordVO.setReplyUserHeadImg(userHeadInfoVO.getHeadImg());
                }
            });
        });


        int size = msgRecordsDao.getMsgSizeByUserId(userId);

        PageResult<List<MsgRecordVO>> pageResult = new PageResult<>();
        pageResult.setTotalCount(size);
        pageResult.setData(msgRecordVOList);
        return pageResult;
    }

    @Override
    public void updateMsgRecordIsRead(int msgRecordId, int readSign) {

        log.info("update | msg_records | update message readSign | params: (id: {}, readSign: {})", msgRecordId, readSign);
        msgRecordsDao.updateMsgRecordIsRead(msgRecordId, readSign);
    }

}
