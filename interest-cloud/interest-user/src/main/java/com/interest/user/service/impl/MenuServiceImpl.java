package com.interest.user.service.impl;

import com.interest.user.dao.MenuDao;
import com.interest.user.dao.RoleDao;
import com.interest.user.model.entity.MenuEntity;
import com.interest.user.model.request.MenuRequest;
import com.interest.user.model.response.MenuIdNameVO;
import com.interest.user.model.response.MenuVO;
import com.interest.user.service.MenuService;
import com.interest.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleService roleService;

    @Override
    public List<MenuVO> menuList(int id) {
        List<String> idList = roleService.getModulesById(id);

        StringBuilder idsTemp = new StringBuilder();
        idList.forEach(idsTemp::append);
        String[] ids = idsTemp.toString().split(";");

        List<MenuVO> childrenMenuVOList = menuDao.getChildMenuListById(ids);
        List<MenuVO> parentMenuVOList = menuDao.getParentMenuListById(childrenMenuVOList.stream().map(MenuVO::getParentId).collect(Collectors.toList()));

        parentMenuVOList.forEach(menuVO -> {
            List<MenuVO> listTemp = new ArrayList<>();
            childrenMenuVOList.forEach(e ->{
                if(menuVO.getId() == e.getParentId()){
                    listTemp.add(e);
                }
            });
            menuVO.setChildren(listTemp);
        });

        return parentMenuVOList;
    }

    @Override
    public List<MenuEntity> menusList(int pageSize, int start, String menuId) {
        return menuDao.menusList(pageSize, start, menuId);
    }

    @Override
    public Integer menusSize(int pageSize, int start, String menuId) {
        return menuDao.menusSize(pageSize, start, menuId);
    }

    @Override
    public List<MenuEntity> menusByParentId(int parentId) {
        return menuDao.menusByParentId(parentId);
    }

    @Override
    public void insertMenu(MenuRequest menuRequest) {
        menuDao.insertMenu(menuRequest);
    }

    @Override
    public void updateMenu(MenuEntity menuEntity) {
        menuDao.updateMenu(menuEntity);
    }

    @Override
    public void deleteMenus(List<String> groupId) {
        menuDao.deleteMenus(groupId);
    }

    @Override
    public List<MenuIdNameVO> getSubmenus() {
        return menuDao.getSubmenus();
    }

}
