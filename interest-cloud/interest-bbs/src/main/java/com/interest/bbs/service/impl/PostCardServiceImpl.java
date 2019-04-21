package com.interest.bbs.service.impl;

import com.interest.bbs.dao.PostCardDao;
import com.interest.bbs.model.entity.PostCardEntity;
import com.interest.bbs.model.request.PostCardRequest;
import com.interest.bbs.model.response.PostCardInfoVO;
import com.interest.bbs.model.response.PostCardNoUserVO;
import com.interest.bbs.model.response.PostCardVO;
import com.interest.bbs.service.PostCardService;
import com.interest.bbs.service.ReplyCardService;
import com.interest.common.feign.InterestUserFeign;
import com.interest.common.model.response.UserHeadInfoVO;
import com.interest.common.utils.DateUtil;
import com.interest.common.utils.SecurityAuthenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostCardServiceImpl implements PostCardService {

    @Autowired
    private PostCardDao postCardDao;

    @Autowired
    private ReplyCardService replyCardService;

    @Autowired
    private InterestUserFeign interestUserFeign;

    @Override
    public List<PostCardVO> postcardList(Integer interestId, int pageSize, int start) {

        List<PostCardVO> postCardVOList = postCardDao.postcardList(interestId, pageSize, start);

        List<UserHeadInfoVO> userHeadInfoVOList = interestUserFeign.getUsersHeadInfoByIds(postCardVOList.stream().map(PostCardVO::getUserId).collect(Collectors.toSet())).getData();

        //组装返回数据
        postCardVOList.forEach((e) -> {
            for (UserHeadInfoVO userHeadInfoVO : userHeadInfoVOList) {
                if (e.getUserId() != null && e.getUserId().intValue() == userHeadInfoVO.getUserId().intValue()) {
                    BeanUtils.copyProperties(userHeadInfoVO, e);
                }
            }
        });

        return postCardVOList;
    }

    @Override
    public Integer postcardSize(Integer interestId, int pageSize, int start) {
        return postCardDao.postcardSize(interestId, pageSize, start);
    }

    @Override
    public void insertEntity(PostCardRequest postCardRequest) {
        int userId = SecurityAuthenUtil.getId();

        PostCardEntity postCardEntity = new PostCardEntity();
        postCardEntity.setInterestId(postCardRequest.getInterestId());
        postCardEntity.setTitle(postCardRequest.getTitle());
        postCardEntity.setContent(postCardRequest.getContent());

        postCardEntity.setUserId(userId);
        postCardEntity.setCreateTime(DateUtil.currentTimestamp());
        postCardEntity.setReplyTime(postCardEntity.getCreateTime());
        log.info("insert | post_card | data : {}", postCardEntity.toString());
        postCardDao.insertEntity(postCardEntity);
    }

    @Override
    public PostCardInfoVO getPostcard(int id) {
        PostCardInfoVO postCardInfoVO = postCardDao.getPostcard(id);
        UserHeadInfoVO userHeadInfoVO = interestUserFeign.getUsersHeadInfoById(postCardInfoVO.getUserId()).getData();
        postCardInfoVO.setUserName(userHeadInfoVO.getUserName());
        postCardInfoVO.setHeadImg(userHeadInfoVO.getHeadImg());
        return postCardInfoVO;
    }

    @Override
    public PostCardInfoVO getPostcardNoUserInfo(int id) {
        return postCardDao.getPostcard(id);
    }

    @Override
    public void updateReplyTime(Integer postCardId, String createTime) {
        log.info("update | post_card | params : (postCardId : {},createTime : {})", postCardId, createTime);
        postCardDao.updateReplyTime(postCardId, createTime);
    }

    @Override
    public List<PostCardNoUserVO> getPostcardsNoUserList(Integer interestId, int pageSize, int start) {
        return postCardDao.getPostcardsNoUserList(interestId,pageSize,start);
    }

    @Override
    public Integer getPostcardsNoUserSize(Integer interestId, int pageSize, int start) {
        return postCardDao.getPostcardsNoUserSize(interestId,pageSize,start);
    }

    @Override
    @Transactional
    public void deletePostcards(List<String> groupId) {
        log.info("delete | post_card | delete postcard | ids: {}",groupId);
        postCardDao.deletePostcards(groupId);
        replyCardService.delReplyByPostcardId(groupId);
    }

}
