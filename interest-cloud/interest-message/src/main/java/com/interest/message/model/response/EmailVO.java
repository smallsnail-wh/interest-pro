package com.interest.message.model.response;

import lombok.Data;

@Data
public class EmailVO {

    private Integer id;

    private String title;

    private String email;

    private String name;

    private String content;

    private String createTime;

    private Integer userId;

}
