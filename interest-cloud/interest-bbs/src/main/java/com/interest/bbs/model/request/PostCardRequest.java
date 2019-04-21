package com.interest.bbs.model.request;

import lombok.Data;

@Data
public class PostCardRequest {

    private String title;

    private String content;

    private Integer interestId;

}
