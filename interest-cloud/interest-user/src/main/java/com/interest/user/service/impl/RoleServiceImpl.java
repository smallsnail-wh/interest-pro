package com.interest.user.service.impl;

import com.interest.user.dao.RoleDao;
import com.interest.user.model.entity.RoleEntity;
import com.interest.user.model.response.RoleVO;
import com.interest.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<String> getModulesById(int userId) {
        return roleDao.getModulesById(userId);
    }

    @Override
    public List<RoleVO> allRoles() {
        return roleDao.allRoles();
    }

    @Override
    public List<RoleEntity> rolesList(int pageSize, int start) {
        return roleDao.rolesList(pageSize, start);
    }

    @Override
    public Integer rolesSize(int pageSize, int start) {
        return roleDao.rolesSize(pageSize, start);
    }

    @Override
    public void insertRole(RoleEntity roleEntity) {
        roleDao.insertRole(roleEntity);
    }

    @Override
    public void updateRole(RoleEntity roleEntity) {
        roleDao.updateRole(roleEntity);
    }

    @Override
    public void deleteRoles(List<String> groupId) {
        roleDao.deleteRoles(groupId);
    }


}
