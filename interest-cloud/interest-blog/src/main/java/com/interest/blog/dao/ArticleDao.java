package com.interest.blog.dao;

import com.interest.blog.model.entity.ArticleEntity;
import com.interest.blog.model.response.ArticleDetailVO;
import com.interest.blog.model.response.ArticleVO;
import com.interest.common.model.PageWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleDao {

    List<ArticleVO> getArticleList(@Param("searchContent") String searchContent, @Param("pageWrapper") PageWrapper pageWrapper);

    Integer getArticleSize(@Param("searchContent") String searchContent);

    void addClickRateById(@Param("number") int number, @Param("id") int id);

    ArticleDetailVO getArticleById(@Param("id") int id);

    void addCommentCountById(@Param("number") int number, @Param("id") Integer articleId);

    void insertArticle(ArticleEntity articleEntity);

    List<ArticleVO> getArticlesListByUserId(@Param("userId") int userId, @Param("pageWrapper") PageWrapper pageWrapper);

    Integer getArticlesSizeByUserId(@Param("userId") int userId);

    void updateArticlesDelByIdAndUserId(@Param("userId") int userId, @Param("articleId") int articleId);

    void updateArticle(ArticleEntity articleEntity);

    List<ArticleVO> getArticleListOnManagement(@Param("searchContent") String searchContent, @Param("dayStart") String dayStart, @Param("dayEnd") String dayEnd, @Param("del") int del, @Param("pageWrapper") PageWrapper pageWrapper);

    Integer getArticleSizeOnManagement(@Param("searchContent") String searchContent, @Param("dayStart") String dayStart, @Param("dayEnd") String dayEnd, @Param("del") int del);

    void updateArticlesDelByIds(@Param("groupId") List<String> groupId, @Param("del") int del);

    void updateArticlesTopByIds(@Param("groupId") List<String> groupId, @Param("top") int top);
}
