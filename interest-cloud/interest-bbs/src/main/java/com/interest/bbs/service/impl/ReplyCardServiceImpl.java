package com.interest.bbs.service.impl;

import com.interest.bbs.dao.ReplyCardDao;
import com.interest.bbs.model.entity.ReplyCardEntity;
import com.interest.bbs.model.request.ReplyCardRequest;
import com.interest.bbs.model.response.ReplyCardVO;
import com.interest.bbs.service.PostCardService;
import com.interest.bbs.service.ReplyCardService;
import com.interest.common.feign.InterestMessageFeign;
import com.interest.common.feign.InterestUserFeign;
import com.interest.common.model.Request.MsgRecodeRequest;
import com.interest.common.model.response.MsgContentVO;
import com.interest.common.model.response.UserHeadInfoVO;
import com.interest.common.utils.DateUtil;
import com.interest.common.utils.SecurityAuthenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReplyCardServiceImpl implements ReplyCardService {

    @Autowired
    private ReplyCardDao replyCardDao;

    @Autowired
    private InterestUserFeign interestUserFeign;

    @Autowired
    private InterestMessageFeign interestMessageFeign;

    @Autowired
    private PostCardService postCardService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public List<ReplyCardVO> replyCardList(int postCardId, int pageSize, int start) {
        List<ReplyCardVO> replyCardVOList = replyCardDao.replyCardList(postCardId, pageSize, start);

        List<UserHeadInfoVO> userHeadInfoVOList = interestUserFeign.getUsersHeadInfoByIds(replyCardVOList.stream().map(ReplyCardVO::getUserId).collect(Collectors.toSet())).getData();

        replyCardVOList.forEach(e->{
            for (UserHeadInfoVO userHeadInfoVO:userHeadInfoVOList){
                if (e.getUserId() != null && e.getUserId().intValue() == userHeadInfoVO.getUserId().intValue()){
                    e.setUserName(userHeadInfoVO.getUserName());
                    e.setHeadImg(userHeadInfoVO.getHeadImg());
                }
            }
        });

        return replyCardVOList;
    }

    @Override
    public Integer replyCardSize(int postCardId, int pageSize, int start) {
        return replyCardDao.replyCardSize(postCardId, pageSize, start);
    }

    @Override
    @Transactional
    public void insertEntity(ReplyCardRequest replyCardRequest) {

        int userId = SecurityAuthenUtil.getId();

        ReplyCardEntity replyCardEntity = new ReplyCardEntity();
        replyCardEntity.setUserId(userId);
        replyCardEntity.setCreateTime(DateUtil.currentTimestamp());
        replyCardEntity.setContent(replyCardRequest.getContent());
        replyCardEntity.setPostCardId(replyCardRequest.getPostCardId());
        log.info("insert | reply_card | data : {}", replyCardEntity.toString());
        replyCardDao.insertEntity(replyCardEntity);

        threadPoolTaskExecutor.execute(()->{
            postCardService.updateReplyTime(replyCardEntity.getPostCardId(), replyCardEntity.getCreateTime());
        });

        MsgRecodeRequest msgRecodeRequest = new MsgRecodeRequest();
        msgRecodeRequest.setOwnerId(postCardService.getPostcardNoUserInfo(replyCardEntity.getPostCardId()).getUserId());
        msgRecodeRequest.setForm(0);
        msgRecodeRequest.setReplyCardId(replyCardEntity.getId());
        msgRecodeRequest.setReplyTime(replyCardEntity.getCreateTime());
        msgRecodeRequest.setIsRead(0);
        interestMessageFeign.insertMessage(msgRecodeRequest);
    }

    @Override
    public List<MsgContentVO> getMsgContentByIds(Set<Integer> ids) {
        return replyCardDao.getMsgContentByIds(ids);
    }

    @Override
    public void delReplyByPostcardId(List<String> postcardIds) {
        log.info("delete | reply_card | delete reply card | postcardIds: {}",postcardIds);
        replyCardDao.delReplyByPostcardId(postcardIds);
    }
}
