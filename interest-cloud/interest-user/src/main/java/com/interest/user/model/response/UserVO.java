package com.interest.user.model.response;

import lombok.Data;

@Data
public class UserVO {

    /**
     * id
     */
    private int id;
    /**
     * 用户名
     */
    private String name;

    private String loginName;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像url
     */
    private String headimg;
    /**
     * GitHub主页
     */
    private String url;
    /**
     * 注册时间
     */
    private String createTime;

    private String githubid;

    private String qqid;

    private Integer type;

}
