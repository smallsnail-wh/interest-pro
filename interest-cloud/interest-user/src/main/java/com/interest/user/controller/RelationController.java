package com.interest.user.controller;

import com.interest.common.model.ResponseWrapper;
import com.interest.user.model.entity.RelationEntity;
import com.interest.user.service.RelationService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RelationController {

    private Logger log = LoggerFactory.getLogger(RelationController.class);

    @Autowired
    private RelationService relationService;

    @ApiOperation("通过userId得到关系List")
    @GetMapping("/admin/relations/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseWrapper<List<RelationEntity>> getRelationByUserId(@PathVariable int userId) {
        return new ResponseWrapper<>(relationService.getRelationByUserId(userId));
    }

    @ApiOperation("批量插入关系数据")
    @PostMapping("/admin/relations")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseWrapper<List<RelationEntity>> insertRelations(@RequestBody() List<RelationEntity> relationList) {
        relationService.insertRelations(relationList);
        return new ResponseWrapper<>(relationList);
    }
}
