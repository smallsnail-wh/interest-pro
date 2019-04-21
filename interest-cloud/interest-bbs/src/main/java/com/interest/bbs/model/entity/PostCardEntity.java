package com.interest.bbs.model.entity;

import lombok.Data;

@Data
public class PostCardEntity {
    private Integer id;

    private String title;

    private String content;

    private Integer interestId;

    private String createTime;

    private String replyTime;

    private Integer userId;

}
