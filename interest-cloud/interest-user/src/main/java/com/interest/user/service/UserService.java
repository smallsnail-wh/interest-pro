package com.interest.user.service;


import com.interest.common.model.response.UserHeadInfoVO;
import com.interest.user.model.request.SystemUserRequest;
import com.interest.user.model.request.UserInfoRequest;
import com.interest.user.model.response.UserBaseInfoVO;
import com.interest.user.model.response.UserInfoVO;
import com.interest.user.model.response.UserVO;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserBaseInfoVO getUserBaseInfoById(int userId);

    List<UserHeadInfoVO> getUsersHeadInfoByIds(Set<Integer> ids);

    UserHeadInfoVO getUsersHeadInfoById(Integer id);

    UserInfoVO getUserInfoById(int userId);

    void updateUserInfoByUserId(int userId, UserInfoRequest userInfoRequest);

    void updateUserHeadImg(int userId, String headImg);

    List<UserVO> getUsersList(String name, Integer userId, Integer status,Integer type, int pageSize, int start);

    Integer getUsersSize(String name, Integer userId, Integer status,Integer type, int pageSize, int start);

    void updateUsersStatus(List<String> groupId,Integer status);

    void insertSystemUser(SystemUserRequest systemUserRequest);

    void updateSystemUser(SystemUserRequest systemUserRequest);
}
