package com.interest.user.model.entity;

import lombok.Data;

@Data
public class UserEntity {
    /**
     * id
     */
    private Integer id;
    /**
     * 姓名
     */
    private String loginName;
    /**
     * 登录名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
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

    private Integer status;

}
