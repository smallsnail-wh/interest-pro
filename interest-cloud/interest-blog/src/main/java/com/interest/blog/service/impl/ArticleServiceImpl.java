package com.interest.blog.service.impl;

import com.interest.blog.dao.ArticleDao;
import com.interest.blog.exception.ArticleException;
import com.interest.blog.model.entity.ArticleEntity;
import com.interest.blog.model.request.ArticleCreateRequest;
import com.interest.blog.model.request.ArticleUpdateRequest;
import com.interest.blog.model.response.ArticleDetailVO;
import com.interest.blog.model.response.ArticleVO;
import com.interest.blog.service.ArticleService;
import com.interest.common.enums.ResponseStatus;
import com.interest.common.feign.InterestUserFeign;
import com.interest.common.model.PageResult;
import com.interest.common.model.PageWrapper;
import com.interest.common.model.response.UserHeadInfoVO;
import com.interest.common.utils.DateUtil;
import com.interest.common.utils.SecurityAuthenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private InterestUserFeign interestUserFeign;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public PageResult<List<ArticleVO>> getArticle(String searchContent, PageWrapper pageWrapper) {
        List<ArticleVO> articleVOList = articleDao.getArticleList(searchContent, pageWrapper);
        articleVOSetUserHeadInfo(articleVOList);

        int size = articleDao.getArticleSize(searchContent);
        return new PageResult<>(articleVOList, size);
    }

    @Override
    public ArticleDetailVO getArticleById(int id) {
        threadPoolTaskExecutor.execute(() -> {
            log.info("update | article | add click rate | params : (id : {},number : {})", id, 1);
            articleDao.addClickRateById(1, id);
        });

        ArticleDetailVO articleDetailVO = articleDao.getArticleById(id);
        if (articleDetailVO != null) {
            UserHeadInfoVO userHeadInfoVO = interestUserFeign.getUsersHeadInfoById(articleDetailVO.getUserId()).getData();
            articleDetailVO.setUserName(userHeadInfoVO.getUserName());
            articleDetailVO.setUserHeadImg(userHeadInfoVO.getHeadImg());
        }

        return articleDetailVO;
    }

    @Override
    public void addCommentCountById(Integer articleId) {
        threadPoolTaskExecutor.execute(() -> {
            log.info("update | article | add comment count | params : (id : {},number : {})", articleId, 1);
            articleDao.addCommentCountById(1, articleId);
        });
    }

    @Override
    @Transactional
    public void createArticle(ArticleCreateRequest articleCreateRequest) {
        int userId = SecurityAuthenUtil.getId();

        Boolean articleSign = stringRedisTemplate.hasKey("article_" + userId);
        if (articleSign != null && articleSign) {
            throw new ArticleException(ResponseStatus.FAIL_6001.getValue(), ResponseStatus.FAIL_6001.getReasonPhrase());
        }

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(articleCreateRequest.getTitle());
        articleEntity.setContent(articleCreateRequest.getContent());
        articleEntity.setCreateTime(DateUtil.currentTimestamp());
        articleEntity.setReplyTime(articleEntity.getCreateTime());
        articleEntity.setUserId(userId);

        String info = htmlText(articleEntity.getContent());
        if (info.length() > 100) {
            info = info.substring(0, 100);
        }
        articleEntity.setInfo(info);

        articleDao.insertArticle(articleEntity);

        stringRedisTemplate.opsForValue().append("article_" + userId, "1");
    }

    /**
     * 截取文章中的文字
     *
     * @param htmlStr 文章
     * @return 文章文字
     */
    private String htmlText(String htmlStr) {
        String textStr = "";
        Pattern p_script;
        java.util.regex.Matcher m_script;
        Pattern p_style;
        java.util.regex.Matcher m_style;
        Pattern p_html;
        java.util.regex.Matcher m_html;
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
        //剔除空格行
        textStr = textStr.replaceAll("[ ]+", " ");
        textStr = textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
        return textStr;// 返回文本字符串
    }

    @Override
    public PageResult<List<ArticleVO>> getArticlesByUserId(int userId, PageWrapper pageWrapper) {
        List<ArticleVO> articleVOList = articleDao.getArticlesListByUserId(userId, pageWrapper);
        articleVOSetUserHeadInfo(articleVOList);

        int size = articleDao.getArticlesSizeByUserId(userId);
        return new PageResult<>(articleVOList, size);
    }

    @Override
    public void updateArticlesDelById(int articleId) {
        int userId = SecurityAuthenUtil.getId();
        articleDao.updateArticlesDelByIdAndUserId(userId, articleId);
    }

    @Override
    public void updateArticle(ArticleUpdateRequest articleCreateRequest) {
        int userId = SecurityAuthenUtil.getId();

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(articleCreateRequest.getId());
        articleEntity.setTitle(articleCreateRequest.getTitle());
        articleEntity.setContent(articleCreateRequest.getContent());
        articleEntity.setUserId(userId);
        String info = htmlText(articleEntity.getContent());
        if (info.length() > 100) {
            info = info.substring(0, 100);
        }
        articleEntity.setInfo(info);

        articleDao.updateArticle(articleEntity);

    }

    @Override
    public PageResult<List<ArticleVO>> getArticleOnManagement(String searchContent, String dateTimestamp, int del, PageWrapper pageWrapper) {
        String dayStart = null;
        String dayEnd = null;
        if (!StringUtils.isEmpty(dateTimestamp)) {
            dayStart = DateUtil.dayStart(dateTimestamp);
            dayEnd = DateUtil.dayEnd(dateTimestamp);
        }
        List<ArticleVO> articleVOList = articleDao.getArticleListOnManagement(searchContent, dayStart, dayEnd, del, pageWrapper);
        //articleVOSetUserHeadInfo(articleVOList);

        int size = articleDao.getArticleSizeOnManagement(searchContent, dayStart, dayEnd, del);
        return new PageResult<>(articleVOList, size);
    }

    /**
     * 给ArticleVO插入用户信息
     *
     * @param articleVOList 文章列表
     */
    private void articleVOSetUserHeadInfo(List<ArticleVO> articleVOList) {
        List<UserHeadInfoVO> userHeadInfoVOList = interestUserFeign.getUsersHeadInfoByIds(articleVOList.stream().map(ArticleVO::getUserId).collect(Collectors.toSet())).getData();
        articleVOList.forEach(e -> {
            userHeadInfoVOList.forEach(userHeadInfoVO -> {
                if (e.getUserId() == userHeadInfoVO.getUserId()) {
                    e.setUserName(userHeadInfoVO.getUserName());
                    e.setUserHeadImg(userHeadInfoVO.getHeadImg());
                }
            });
        });
    }

    @Override
    public void updateArticlesDelByIds(List<String> groupId, int del) {
        articleDao.updateArticlesDelByIds(groupId, del);
    }

    @Override
    public void updateArticlesTopByIds(List<String> groupId, int top) {
        articleDao.updateArticlesTopByIds(groupId, top);
    }

}
