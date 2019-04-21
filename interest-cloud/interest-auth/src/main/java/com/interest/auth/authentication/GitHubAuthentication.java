package com.interest.auth.authentication;

import com.interest.auth.exception.LoginFailureExcepiton;
import com.interest.auth.model.entity.UserDetailEntity;
import com.interest.auth.model.entity.UserEntity;
import com.interest.auth.model.entity.UserGithubEntity;
import com.interest.auth.picture.PictureService;
import com.interest.auth.properties.GithubProperties;
import com.interest.auth.service.UserDetailService;
import com.interest.auth.service.UserGithubService;
import com.interest.auth.service.UserService;
import com.interest.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service(value = "gitHubAuthentication")
public class GitHubAuthentication implements MyAuthentication {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserGithubService userGithubService;

    @Autowired
    private GithubProperties githubProperties;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * GitHub token 请求地址
     */
    private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    /**
     * GitHub 用户信息请求地址
     */
    private static final String GITHUB_USER_URL = "https://api.github.com/user";

    @Override
    @Transactional
    public String getUserId(String code) {

        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("client_id", githubProperties.getClientId());
        requestEntity.add("client_secret", githubProperties.getClientSecret());
        requestEntity.add("code", code);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GITHUB_ACCESS_TOKEN_URL, requestEntity, String.class);

        String message = responseEntity.getBody().trim();

        String access_token = message.split("&")[0].split("=")[1];

        if (access_token == null || "".equals(access_token)) {
            throw new LoginFailureExcepiton(message);
        }

        String url = GITHUB_USER_URL + "?access_token=" + access_token;

        responseEntity = restTemplate.getForEntity(url, String.class);

        UserEntity userEntity = null;

        try {

            JSONObject githubUserInfo = new JSONObject(responseEntity.getBody().trim());

            String login = githubUserInfo.getString("login");

            if (login == null) {
                throw new LoginFailureExcepiton(githubUserInfo.toString());
            }

            userEntity = userService.getEntityByGithubId(login);


            if (userEntity == null) {
                return insertUser(githubUserInfo);
            } else {
                return String.valueOf(userEntity.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String insertUser(JSONObject githubToken) throws JSONException {
        String headImg = githubToken.getString("avatar_url");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(githubToken.getString("email"));
        userEntity.setHeadimg(headImg);
        userEntity.setName(githubToken.getString("login"));
        userEntity.setUrl(githubToken.getString("html_url"));
        userEntity.setGithubid(githubToken.getString("login"));
        userEntity.setCreateTime(DateUtil.currentTimestamp());
        userService.insertUser(userEntity);

        UserGithubEntity userGithubEntity = new UserGithubEntity();
        userGithubEntity.setLogin(githubToken.getString("login"));
        userGithubEntity.setAvatarUrl(githubToken.getString("avatar_url"));
        userGithubEntity.setHtmlUrl(githubToken.getString("html_url"));
        userGithubEntity.setEmail(githubToken.getString("email"));
        userGithubEntity.setUserid(userEntity.getId());
        userGithubService.insertEntity(userGithubEntity);

        UserDetailEntity userDetailEntity = new UserDetailEntity();
        userDetailEntity.setUserid(userEntity.getId());
        userDetailService.insert(userDetailEntity);

        // 异步将网络资源下载到本地，并且更新数据库
        threadPoolTaskExecutor.execute(() -> {
            userService.updateUserHeadImg(userEntity.getId(), pictureService.saveImage(headImg, "/head", "png"));
        });
        return String.valueOf(userEntity.getId());
    }

}
