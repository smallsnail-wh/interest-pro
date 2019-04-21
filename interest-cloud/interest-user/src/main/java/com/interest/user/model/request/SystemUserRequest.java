package com.interest.user.model.request;

import lombok.Data;

@Data
public class SystemUserRequest {

    private Integer id;

    private String name;

    private String loginName;

    private String password;

}
