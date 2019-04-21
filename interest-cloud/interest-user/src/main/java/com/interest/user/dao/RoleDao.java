package com.interest.user.dao;

import com.interest.user.model.entity.RoleEntity;
import com.interest.user.model.response.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDao {

    List<String> getModulesById(@Param("userId") int userId);

    List<RoleVO> allRoles();

    /**
     * 获取role列表
     *
     * @param pageSize
     * @param start
     * @return
     */
    List<RoleEntity> rolesList(@Param("pageSize") int pageSize, @Param("start") int start);

    /**
     * 获取role列表的总量
     *
     * @param pageSize
     * @param start
     * @return
     */
    Integer rolesSize(@Param("pageSize") int pageSize, @Param("start") int start);

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
    void deleteRoles(@Param("groupId") List<String> groupId);
}
