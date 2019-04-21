package com.interest.common.feign;

import com.interest.common.model.ResponseWrapper;
import com.interest.common.model.response.MsgContentVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "interest-blog")
public interface InterestBlogFeign {

    String INTEREST_USER_BASE_URL = "/interest/blog";

    @PostMapping(INTEREST_USER_BASE_URL + "/article/comments/ids")
    ResponseWrapper<List<MsgContentVO>> getMsgContentByIds(@RequestBody Set<Integer> ids);

    @DeleteMapping(INTEREST_USER_BASE_URL + "/picture")
    ResponseWrapper<Boolean> deletePicture(@RequestParam("url") String url);

}
