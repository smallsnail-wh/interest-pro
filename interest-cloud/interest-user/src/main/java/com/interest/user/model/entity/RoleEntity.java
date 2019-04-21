package com.interest.user.model.entity;

import lombok.Data;

@Data
public class RoleEntity {
    /**
     * id
     */
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
     * 权限
     */
    private String modules;
    /**
     * 描述
     */
    private String describe;

}
