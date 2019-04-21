package com.interest.user.service;

import com.interest.user.model.entity.RoleEntity;
import com.interest.user.model.response.RoleVO;

import java.util.List;

public interface RoleService {

    List<String> getModulesById(int userId);

    List<RoleVO> allRoles();

    /**
     * 获取role列表
     *
     * @param pageSize
     * @param start
     * @return
     */
    List<RoleEntity> rolesList(int pageSize, int start);

    /**
     * 获取role列表的总量
     *
     * @param pageSize
     * @param start
     * @return
     */
    Integer rolesSize(int pageSize, int start);

    /**
     * 新建角色信息
     *
     * @param roleEntity
     */
    void insertRole(RoleEntity roleEntity);

    /**
     * 更新角色信息
     *
     * @param roleEntity
     */
    void updateRole(RoleEntity roleEntity);

    /**
     * 删除角色信息
     *
     * @param groupId
     */
    void deleteRoles(List<String> groupId);

}
