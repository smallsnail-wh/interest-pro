package com.interest.auth.model.entity;

import lombok.Data;

@Data
public class UserGithubEntity {

    private String login;

    private String avatarUrl;

    private String htmlUrl;

    private String email;

    private Integer userid;

}
