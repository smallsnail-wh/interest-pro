package com.interest.message.controller;

import com.interest.common.model.PageResult;
import com.interest.common.model.Request.MsgRecodeRequest;
import com.interest.common.model.ResponseWrapper;
import com.interest.common.utils.SecurityAuthenUtil;
import com.interest.message.service.MsgRecordsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MsgRecordsController {

    @Autowired
    private MsgRecordsService msgRecordsService;

    @ApiOperation("获取当前用户未读消息数量")
    @GetMapping("/msgrecords/unreadnum")
    public ResponseWrapper<Integer> getUnreadMsgCount() {
        return new ResponseWrapper<>(msgRecordsService.getUnreadMsgCount(SecurityAuthenUtil.getId()));
    }

    @ApiOperation("添加消息")
    @PostMapping("/messages/message")
    ResponseWrapper<String> insertMessage(@RequestBody MsgRecodeRequest msgRecodeRequest){
        msgRecordsService.insertMessage(msgRecodeRequest);
        return new ResponseWrapper<>("success");
    }

    @ApiOperation("查询当前用户消息")
    @GetMapping("/msg-records/user")
    public ResponseWrapper<PageResult> userMesRecordsGet(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page) {
        PageResult pageResult = msgRecordsService.getUserMegsResult(pageSize, page * pageSize);
        return new ResponseWrapper<>(pageResult);
    }

    @ApiOperation("消息已读")
    @PutMapping("/messages/message/read")
    public ResponseWrapper<Integer> userReadMes(@RequestParam("msgRecordId") Integer msgRecordId) {
        msgRecordsService.updateMsgRecordIsRead(msgRecordId, 1);
        return new ResponseWrapper<Integer>(msgRecordId);
    }

}
