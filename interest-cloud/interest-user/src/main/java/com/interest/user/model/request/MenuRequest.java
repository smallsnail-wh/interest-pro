package com.interest.user.model.request;

import lombok.Data;

@Data
public class MenuRequest {

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
     * 排序
     */
    private int sort;
    /**
     * 描述
     */
    private String remark;
    /**
     * 图标
     */
    private String icon;

}
