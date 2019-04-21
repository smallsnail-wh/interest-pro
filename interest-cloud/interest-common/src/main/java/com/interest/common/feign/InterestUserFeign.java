package com.interest.common.feign;

import com.interest.common.model.PageResult;
import com.interest.common.model.ResponseWrapper;
import com.interest.common.model.response.UserHeadInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "interest-user")
public interface InterestUserFeign {

    String INTEREST_USER_BASE_URL = "/interest/user";

    @PostMapping(INTEREST_USER_BASE_URL + "/public/users/ids")
    ResponseWrapper<List<UserHeadInfoVO>> getUsersHeadInfoByIds(@RequestBody Set<Integer> ids);

    @GetMapping(INTEREST_USER_BASE_URL + "/public/users/user/id")
    ResponseWrapper<UserHeadInfoVO> getUsersHeadInfoById(@RequestParam("userId") Integer userId);
}
