package com.interest.bbs.model.response;

import lombok.Data;

@Data
public class PostCardInfoVO {

    private Integer id;

    private String title;

    private String content;

    private String createTime;

    private Integer userId;

    private String headImg;

    private String userName;

}
