package com.interest.user.dao;

import com.interest.user.model.entity.MenuEntity;
import com.interest.user.model.request.MenuRequest;
import com.interest.user.model.response.MenuIdNameVO;
import com.interest.user.model.response.MenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface MenuDao {

    List<MenuVO> getMenuListById(@Param("ids") String[] ids);

    List<MenuVO> getChildMenuListById(@Param("ids") String[] ids);

    /**
     * 通过用户Id得到一级菜单List
     *
     * @param ids
     * @return
     */
    List<MenuVO> getParentMenuListById(@Param("ids") List<Integer> ids);

    /**
     * 获取menus列表
     *
     * @param pageSize
     * @param start
     * @param menuId
     * @return
     */
    List<MenuEntity> menusList(@Param("pageSize") int pageSize, @Param("start") int start,
                               @Param("menuId") String menuId);

    /**
     * 获取menus列表的总量
     *
     * @param pageSize
     * @param start
     * @param menuId
     * @return
     */
    Integer menusSize(@Param("pageSize") int pageSize, @Param("start") int start,
                      @Param("menuId") String menuId);

    /**
     * 通过parentId得到menus列表
     *
     * @param parentId
     * @return
     */
    List<MenuEntity> menusByParentId(@Param("parentId") int parentId);

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
    void deleteMenus(@Param("groupId") List<String> groupId);

    /**
     * 获取二级菜单
     *
     * @return
     */
    List<MenuIdNameVO> getSubmenus();

}
