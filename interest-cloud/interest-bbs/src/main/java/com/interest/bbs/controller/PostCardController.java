package com.interest.bbs.controller;

import com.interest.bbs.model.request.PostCardRequest;
import com.interest.bbs.model.response.PostCardInfoVO;
import com.interest.bbs.model.response.PostCardNoUserVO;
import com.interest.bbs.model.response.PostCardVO;
import com.interest.bbs.service.PostCardService;
import com.interest.common.model.PageResult;
import com.interest.common.model.ResponseWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostCardController {

    @Autowired
    private PostCardService postCardService;

    @ApiOperation("获取发帖")
    @GetMapping("/public/postcards")
    public ResponseWrapper<PageResult> postcardList(@RequestParam(value = "interestId", required = false) Integer interestId,
                                                    @RequestParam("pageSize") int pageSize, @RequestParam("page") int page) {
        PageResult<List<PostCardVO>> pageResult = new PageResult<>();
        pageResult.setData(postCardService.postcardList(interestId, pageSize, page * pageSize));
        pageResult.setTotalCount(postCardService.postcardSize(interestId, pageSize, page * pageSize));
        return new ResponseWrapper<>(pageResult);
    }

    @ApiOperation("发帖请求")
    @PostMapping("/postcards/postcard")
    public ResponseWrapper<String> insertEntity(@RequestBody PostCardRequest postCardRequest) {
        postCardService.insertEntity(postCardRequest);
        return new ResponseWrapper<>("success");
    }

    @ApiOperation("获取单个发帖")
    @GetMapping("/public/postcards/postcard")
    public ResponseWrapper<PostCardInfoVO> postcardGet(@RequestParam("id") int id) {
        return new ResponseWrapper<>(postCardService.getPostcard(id));
    }

    @ApiOperation("获取发帖,不加用户信息")
    @GetMapping("/admin/postcards")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<PageResult> getPostcardsNoUser(@RequestParam(value = "interestId", required = false) Integer interestId,
                                                    @RequestParam("pageSize") int pageSize, @RequestParam("page") int page) {
        PageResult<List<PostCardNoUserVO>> pageResult = new PageResult<>();
        pageResult.setData(postCardService.getPostcardsNoUserList(interestId, pageSize, page * pageSize));
        pageResult.setTotalCount(postCardService.getPostcardsNoUserSize(interestId, pageSize, page * pageSize));
        return new ResponseWrapper<>(pageResult);
    }

    @ApiOperation("删除发帖")
    @DeleteMapping("/postcards")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<List<String>> deletePostcards(@RequestBody List<String> groupId) {
        postCardService.deletePostcards(groupId);
        return new ResponseWrapper<>(groupId);
    }

}
