package com.interest.user.model.response;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {

    /**
     * id
     */
    private int id;
    /**
     * 菜单名
     */
    private String name;
    /**
     * url
     */
    private String url;
    /**
     * 父类id
     */
    private int parentId;
    /**
     * 图标
     */
    private String icon;
    /**
     * 二级菜单
     */
    private List<MenuVO> children;
}
