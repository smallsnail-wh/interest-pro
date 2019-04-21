package com.interest.bbs.controller;

import com.interest.bbs.model.request.InterestRequest;
import com.interest.bbs.model.request.InterestUpdateRequest;
import com.interest.bbs.model.response.BannerVO;
import com.interest.bbs.model.response.InterestNoContentVO;
import com.interest.bbs.model.response.InterestOnlyTitleVO;
import com.interest.bbs.model.response.InterestVO;
import com.interest.bbs.service.InterestService;
import com.interest.common.model.PageResult;
import com.interest.common.model.ResponseWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InterestController {

    @Autowired
    private InterestService interestService;

    @ApiOperation("获取首页banner数据")
    @GetMapping("/public/banners")
    public ResponseWrapper<List<BannerVO>> bannersGet() {
        return new ResponseWrapper<>(interestService.getBanners());
    }

    @ApiOperation("获取首页兴趣")
    @GetMapping("/public/interests")
    public ResponseWrapper<List<InterestNoContentVO>> interestGet(@RequestParam(value = "title", required = false) String title) {
        return new ResponseWrapper<>(interestService.getInterest(title));
    }

    @ApiOperation("获取兴趣详情")
    @GetMapping("/public/interests/interest")
    public ResponseWrapper<InterestVO> interestGetById(@RequestParam(value = "id") int id) {
        return new ResponseWrapper<>(interestService.getInterestById(id));
    }

    @ApiOperation("获取兴趣ID+title")
    @GetMapping("/admin/interests/titles")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<List<InterestOnlyTitleVO>> getInterestsTitles() {
        return new ResponseWrapper<>(interestService.getInterestsTitles());
    }

    @ApiOperation("添加兴趣")
    @PostMapping("/admin/interests/interest")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<String> interestInsert(@RequestBody InterestRequest interestRequest) {

        interestService.insertEntity(interestRequest);

        return new ResponseWrapper<>("success");
    }

    @ApiOperation("修改兴趣")
    @PutMapping("/admin/interests/interest")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<String> interestUpdate(@RequestBody InterestUpdateRequest interestUpdateRequest) {

        interestService.updateEntity(interestUpdateRequest);

        return new ResponseWrapper<>("success");
    }

    @ApiOperation("控制台获取兴趣接口")
    @GetMapping("/admin/interests")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<PageResult> interestList(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page) {
        PageResult<List<InterestNoContentVO>> pageResult = new PageResult<>();
        pageResult.setData(interestService.interestList(pageSize, page * pageSize));
        pageResult.setTotalCount(interestService.interestSize());
        return new ResponseWrapper<>(pageResult);
    }

    @ApiOperation("删除兴趣")
    @DeleteMapping("/admin/interests")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<List<String>> deleteInterests(@RequestBody List<String> groupId) {
        interestService.deleteInterests(groupId);
        return new ResponseWrapper<>(groupId);
    }

    @ApiOperation("添加banner")
    @PutMapping("/admin/banners/set")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<List<String>> bannersSet(@RequestBody List<String> groupId) {
        interestService.updateBanners(groupId, 1);
        return new ResponseWrapper<>(groupId);
    }

    @ApiOperation("取消banner")
    @PutMapping("/admin/banners/del")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_BASE_ADMIN')")
    public ResponseWrapper<List<String>> bannersDel(@RequestBody List<String> groupId) {
        interestService.updateBanners(groupId, 0);
        return new ResponseWrapper<>(groupId);
    }

}
