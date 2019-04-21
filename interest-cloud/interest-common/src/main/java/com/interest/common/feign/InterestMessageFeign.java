package com.interest.common.feign;

import com.interest.common.model.Request.MsgRecodeRequest;
import com.interest.common.model.ResponseWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "interest-message")
public interface InterestMessageFeign {

    String INTEREST_MESSAGE_BASE_URL = "/interest/message";

    @PostMapping(INTEREST_MESSAGE_BASE_URL + "/messages/message")
    ResponseWrapper<String> insertMessage(@RequestBody MsgRecodeRequest msgRecodeRequest);

}
