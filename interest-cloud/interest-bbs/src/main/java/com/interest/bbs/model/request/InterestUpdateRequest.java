package com.interest.bbs.model.request;

import lombok.Data;

@Data
public class InterestUpdateRequest {

    private Integer id;

    private String title;

    private String info;

    private String image;

    private String content;

    private Integer sort;

}
