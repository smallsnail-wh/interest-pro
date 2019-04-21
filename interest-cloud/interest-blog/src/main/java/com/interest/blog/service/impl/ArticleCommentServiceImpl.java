package com.interest.blog.service.impl;

import com.interest.blog.dao.ArticleCommentDao;
import com.interest.blog.model.entity.ArticleCommentEntity;
import com.interest.blog.model.request.ArticleCommentRequest;
import com.interest.blog.model.response.ArticleCommentVO;
import com.interest.blog.model.response.ArticleReplyCommentVO;
import com.interest.blog.service.ArticleCommentService;
import com.interest.blog.service.ArticleService;
import com.interest.common.feign.InterestMessageFeign;
import com.interest.common.feign.InterestUserFeign;
import com.interest.common.model.PageResult;
import com.interest.common.model.PageWrapper;
import com.interest.common.model.Request.MsgRecodeRequest;
import com.interest.common.model.response.MsgContentVO;
import com.interest.common.model.response.UserHeadInfoVO;
import com.interest.common.utils.DateUtil;
import com.interest.common.utils.SecurityAuthenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentDao articleCommentDao;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private InterestMessageFeign interestMessageFeign;

    @Autowired
    private InterestUserFeign interestUserFeign;

    @Override
    public void putArticleCommentService(ArticleCommentRequest articleCommentRequest) {

        int userId = SecurityAuthenUtil.getId();

        ArticleCommentEntity articleCommentEntity = new ArticleCommentEntity();
        articleCommentEntity.setArticleId(articleCommentRequest.getArticleId());
        articleCommentEntity.setUserId(userId);
        articleCommentEntity.setParentId(articleCommentRequest.getParentId());
        articleCommentEntity.setComment(articleCommentRequest.getComment());
        articleCommentEntity.setCreateTime(DateUtil.currentTimestamp());
        articleCommentEntity.setReplierId(articleCommentRequest.getReplierId());
        articleCommentEntity.setReplierName(articleCommentRequest.getReplierName());

        log.info("insert | article_comment | data : {}", articleCommentEntity.toString());
        articleCommentDao.insertEntity(articleCommentEntity);

        articleService.addCommentCountById(articleCommentEntity.getArticleId());

        MsgRecodeRequest msgRecodeRequest = new MsgRecodeRequest();
        msgRecodeRequest.setOwnerId(articleCommentRequest.getArticleOwnerId());
        msgRecodeRequest.setForm(1);
        msgRecodeRequest.setCommentId(articleCommentEntity.getId());
        msgRecodeRequest.setReplyTime(articleCommentEntity.getCreateTime());
        msgRecodeRequest.setIsRead(0);
        interestMessageFeign.insertMessage(msgRecodeRequest);

        if (articleCommentRequest.getReplierId() != null) {
            MsgRecodeRequest commentMsgRecordRequest = new MsgRecodeRequest();
            commentMsgRecordRequest.setOwnerId(articleCommentRequest.getReplierId());
            commentMsgRecordRequest.setForm(1);
            commentMsgRecordRequest.setCommentId(articleCommentEntity.getId());
            commentMsgRecordRequest.setReplyTime(articleCommentEntity.getCreateTime());
            commentMsgRecordRequest.setIsRead(0);
            interestMessageFeign.insertMessage(commentMsgRecordRequest);
        }

    }

    @Override
    public PageResult<List<ArticleCommentVO>> getArticleCommentByArticleId(int articleId, PageWrapper pageWrapper) {

        List<ArticleCommentVO> list = articleCommentDao.getArticleCommentByArticleId(articleId, pageWrapper);
        Set<Integer> userIdSet = list.stream().map(ArticleCommentVO::getUserId).collect(Collectors.toSet());
        list.forEach(e -> {
            e.setChildCommentsCount(e.getChildComments().size());
            if (!CollectionUtils.isEmpty(e.getChildComments())) {
                userIdSet.addAll(e.getChildComments().stream().map(ArticleReplyCommentVO::getUserId).collect(Collectors.toSet()));
            }
        });
        List<UserHeadInfoVO> userHeadInfoVOList = interestUserFeign.getUsersHeadInfoByIds(userIdSet).getData();
        list.forEach(e -> {
            userHeadInfoVOList.forEach(userHeadInfoVO -> {
                if(e.getUserId() != null && e.getUserId().intValue() == userHeadInfoVO.getUserId().intValue()){
                    e.setUserName(userHeadInfoVO.getUserName());
                    e.setUserHeadImg(userHeadInfoVO.getHeadImg());
                }
            });
            e.getChildComments().forEach(m->{
                userHeadInfoVOList.forEach(userHeadInfoVO -> {
                    if(m.getUserId() != null && m.getUserId().intValue() == userHeadInfoVO.getUserId().intValue()){
                        m.setUserName(userHeadInfoVO.getUserName());
                        m.setUserHeadImg(userHeadInfoVO.getHeadImg());
                    }
                });
            });
        });

        int size = articleCommentDao.getArticleCommentSizeByArticleId(articleId);

        return new PageResult<>(list, size);
    }

    @Override
    public List<MsgContentVO> getMsgContentByIds(Set<Integer> ids) {
        return articleCommentDao.getMsgContentByIds(ids);
    }
}
