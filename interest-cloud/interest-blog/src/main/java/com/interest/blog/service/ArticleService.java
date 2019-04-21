package com.interest.blog.service;

import com.interest.blog.model.request.ArticleCreateRequest;
import com.interest.blog.model.request.ArticleUpdateRequest;
import com.interest.blog.model.response.ArticleDetailVO;
import com.interest.blog.model.response.ArticleVO;
import com.interest.common.model.PageResult;
import com.interest.common.model.PageWrapper;

import java.util.List;

public interface ArticleService {

    PageResult getArticle(String searchContent, PageWrapper pageWrapper);

    ArticleDetailVO getArticleById(int id);

    void addCommentCountById(Integer articleId);

    void createArticle(ArticleCreateRequest articleCreateRequest);

    PageResult getArticlesByUserId(int userId, PageWrapper pageWrapper);

    void updateArticlesDelById(int articleId);

    void updateArticle(ArticleUpdateRequest articleCreateRequest);

    PageResult<List<ArticleVO>> getArticleOnManagement(String searchContent, String dateTimestamp, int del, PageWrapper pageWrapper);

    void updateArticlesDelByIds(List<String> groupId, int del);

    void updateArticlesTopByIds(List<String> groupId, int top);
}
