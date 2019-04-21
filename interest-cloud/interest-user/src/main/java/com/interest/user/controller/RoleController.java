package com.interest.user.controller;

import com.interest.common.model.PageResult;
import com.interest.common.model.ResponseWrapper;
import com.interest.user.model.entity.RoleEntity;
import com.interest.user.model.response.RoleVO;
import com.interest.user.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("得到角色全部数据")
    @GetMapping("/roles/all")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseWrapper<List<RoleVO>> allRoles() {
        return new ResponseWrapper<>(roleService.allRoles());
    }

    @ApiOperation("获取role表数据")
    @GetMapping("/admin/roles")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseWrapper<PageResult<List<RoleEntity>>> rolesList(int pageSize, int page) {
        PageResult<List<RoleEntity>> pageResult = new PageResult<>();
        pageResult.setData(roleService.rolesList(pageSize, page * pageSize));
        pageResult.setTotalCount(roleService.rolesSize(pageSize, page * pageSize));
        return new ResponseWrapper<>(pageResult);
    }

    @ApiOperation("新建角色信息")
    @PostMapping("/admin/roles/role")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseWrapper<RoleEntity> insertRole(@RequestBody RoleEntity roleEntity) {
        roleService.insertRole(roleEntity);
        return new ResponseWrapper<>(roleEntity);
    }

    @ApiOperation("更新角色信息")
    @PutMapping("/admin/roles/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseWrapper<RoleEntity> updateRole(@RequestBody RoleEntity roleEntity, @PathVariable int id) {
        if (roleEntity.getId() == id) {
            roleService.updateRole(roleEntity);
        }
        return new ResponseWrapper<>(roleEntity);
    }

    @ApiOperation("删除角色信息")
    @DeleteMapping("/admin/roles")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseWrapper<List<String>> deleteRoles(@RequestBody List<String> groupId) {
        roleService.deleteRoles(groupId);
        return new ResponseWrapper<>(groupId);
    }
}
