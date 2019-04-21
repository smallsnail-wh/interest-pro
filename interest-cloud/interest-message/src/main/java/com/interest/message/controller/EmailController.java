package com.interest.message.controller;

import com.interest.common.model.PageResult;
import com.interest.common.model.ResponseWrapper;
import com.interest.common.utils.SecurityAuthenUtil;
import com.interest.message.model.entity.EmailEntity;
import com.interest.message.model.response.EmailVO;
import com.interest.message.service.EmailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @ApiOperation("发送email")
    @PostMapping("/email")
    public ResponseWrapper<EmailEntity> insertEntity(@RequestBody EmailEntity emailEntity) {
        int userId = SecurityAuthenUtil.getId();
        emailEntity.setUserId(userId);
        emailService.insertEntity(emailEntity);
        return new ResponseWrapper<>(emailEntity);
    }

    @ApiOperation("查询邮件")
    @GetMapping("/emails")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<PageResult<List<EmailVO>>> getEmailsList(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page) {
        PageResult<List<EmailVO>> pageResult = new PageResult<>();
        pageResult.setData(emailService.getEmailsList(pageSize, page * pageSize));
        pageResult.setTotalCount(emailService.getEmailsSize(pageSize, page * pageSize));
        return new ResponseWrapper<>(pageResult);
    }

    @ApiOperation("删除邮件")
    @DeleteMapping("/emails")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<List<String>> deleteEmails(@RequestBody List<String> groupId) {
        emailService.deleteEmails(groupId);
        return new ResponseWrapper<>(groupId);
    }

}
