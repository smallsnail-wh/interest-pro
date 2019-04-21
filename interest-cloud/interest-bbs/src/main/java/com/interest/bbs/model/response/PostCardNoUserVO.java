package com.interest.bbs.model.response;

import lombok.Data;

@Data
public class PostCardNoUserVO {

    private Integer id;

    private String title;

    private String content;

    private String interestId;

    private String createTime;

    private String replyTime;

    private Integer userId;

}
