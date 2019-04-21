package com.interest.user.model.response;

import lombok.Data;

@Data
public class RoleVO {

    private int id;
    /**
     * 角色
     */
    private String role;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 描述
     */
    private String describe;

}
