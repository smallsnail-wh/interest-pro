package com.interest.user.service;

import com.interest.user.model.entity.MenuEntity;
import com.interest.user.model.request.MenuRequest;
import com.interest.user.model.response.MenuIdNameVO;
import com.interest.user.model.response.MenuVO;

import java.util.List;

public interface MenuService {

    /**
     * 得到菜单List
     *
     * @param id
     * @return
     */
    List<MenuVO> menuList(int id);

    /**
     * 获取menus列表
     *
     * @param pageSize
     * @param start
     * @param menuId
     * @return
     */
    List<MenuEntity> menusList(int pageSize, int start, String menuId);

    /**
     * 获取menus列表的总量
     *
     * @param pageSize
     * @param start
     * @param menuId
     * @return
     */
    Integer menusSize(int pageSize, int start, String menuId);


    /**
     * 通过parentId得到menus列表
     *
     * @param parentId
     * @return
     */
    List<MenuEntity> menusByParentId(int parentId);

    /**
     * 新建菜单信息
     *
     * @param menuRequest
     */
    void insertMenu(MenuRequest menuRequest);

    /**
     * 修改菜单信息
     *
     * @param menuEntity
     */
    void updateMenu(MenuEntity menuEntity);

    /**
     * 删除菜单信息
     *
     * @param groupId
     */
    void deleteMenus(List<String> groupId);

    /**
     * 获取二级菜单
     *
     * @return
     */
    List<MenuIdNameVO> getSubmenus();

}
