package com.interest.bbs.model.response;

import lombok.Data;

@Data
public class PostCardVO {

    private Integer id;

    private String title;

    private String content;

    private String replyTime;

    private Integer userId;

    private String headImg;

    private String userName;

    private Integer replyCount;

}
