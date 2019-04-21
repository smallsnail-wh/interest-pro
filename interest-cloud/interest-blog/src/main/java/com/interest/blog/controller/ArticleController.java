package com.interest.blog.controller;

import com.interest.blog.model.request.ArticleCreateRequest;
import com.interest.blog.model.request.ArticleUpdateRequest;
import com.interest.blog.model.response.ArticleDetailVO;
import com.interest.blog.model.response.ArticleVO;
import com.interest.blog.picture.PictureService;
import com.interest.blog.service.ArticleService;
import com.interest.common.model.PageResult;
import com.interest.common.model.PageWrapper;
import com.interest.common.model.ResponseWrapper;
import com.interest.common.utils.SecurityAuthenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("首页文章获取")
    @GetMapping("/public/articles")
    public ResponseWrapper<PageResult> getArticle(@RequestParam(value = "searchContent", required = false) String searchContent,
                                                  @RequestParam("pageSize") int pageSize,
                                                  @RequestParam("page") int page) {
        PageWrapper pageWrapper = new PageWrapper(pageSize, page);
        PageResult pageResult = articleService.getArticle(searchContent, pageWrapper);
        return new ResponseWrapper<>(pageResult);
    }

    @ApiOperation("文章详情获取")
    @GetMapping("/public/articles/article/{id}")
    public ResponseWrapper<ArticleDetailVO> getArticleById(@PathVariable("id") int id) {
        ArticleDetailVO articleDetailVO = articleService.getArticleById(id);
        return new ResponseWrapper<>(articleDetailVO);
    }

    @ApiOperation("发布文章")
    @PostMapping("/articles/article")
    public ResponseWrapper<String> createArticle(@RequestBody ArticleCreateRequest articleCreateRequest) {
        articleService.createArticle(articleCreateRequest);
        return new ResponseWrapper<>("success");
    }

    @ApiOperation("用户文章获取")
    @GetMapping("/general/articles/user")
    public ResponseWrapper<PageResult> getUserArticle(@RequestParam("pageSize") int pageSize,
                                                      @RequestParam("page") int page) {
        int userId = SecurityAuthenUtil.getId();
        PageWrapper pageWrapper = new PageWrapper(pageSize, page);
        PageResult pageResult = articleService.getArticlesByUserId(userId, pageWrapper);
        return new ResponseWrapper<>(pageResult);
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/articles/article/{id}")
    public ResponseWrapper delArticleById(@PathVariable("id") int articleId) {
        articleService.updateArticlesDelById(articleId);
        return new ResponseWrapper<>(articleId);
    }

    @PutMapping("/article")
    public ResponseWrapper<String> updateArticle(@RequestBody ArticleUpdateRequest articleCreateRequest) {
        articleService.updateArticle(articleCreateRequest);
        return new ResponseWrapper<>("success");
    }

    @ApiOperation("通过用户id，获取用户文章")
    @GetMapping("/public/articles/user/{userId}")
    public ResponseWrapper<PageResult> getArticle(@PathVariable("userId") int userId,
                                                  @RequestParam("pageSize") int pageSize,
                                                  @RequestParam("page") int page) {
        PageWrapper pageWrapper = new PageWrapper(pageSize, page);
        PageResult pageResult = articleService.getArticlesByUserId(userId, pageWrapper);
        return new ResponseWrapper<>(pageResult);
    }

    @ApiOperation("控制台获取文章")
    @GetMapping("/admin/articles")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<PageResult<List<ArticleVO>>> getArticles(@RequestParam(value = "searchContent", required = false) String searchContent,
                                                              @RequestParam(value = "dateTimestamp", required = false) String dateTimestamp,
                                                              @RequestParam(value = "del", required = false) int del,
                                                              @RequestParam("pageSize") int pageSize,
                                                              @RequestParam("page") int page) {
        PageWrapper pageWrapper = new PageWrapper(pageSize, page);
        PageResult<List<ArticleVO>> pageResult = articleService.getArticleOnManagement(searchContent, dateTimestamp, del, pageWrapper);
        return new ResponseWrapper<>(pageResult);
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/admin/articles")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper delArticles(@RequestBody List<String> groupId) {
        articleService.updateArticlesDelByIds(groupId, 1);
        return new ResponseWrapper<>(groupId);
    }

    @ApiOperation("重新发布文章")
    @PatchMapping("/admin/articles")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper republishArticles(@RequestBody List<String> groupId) {
        articleService.updateArticlesDelByIds(groupId, 0);
        return new ResponseWrapper<>(groupId);
    }

    @ApiOperation("修改文章置顶状态")
    @PatchMapping("/admin/articles/top")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper changeArticlesTop(@RequestBody List<String> groupId, @RequestParam("top") int top) {
        articleService.updateArticlesTopByIds(groupId, top);
        return new ResponseWrapper<>(groupId);
    }

}
