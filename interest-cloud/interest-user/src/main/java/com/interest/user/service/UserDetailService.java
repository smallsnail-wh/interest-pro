package com.interest.user.service;


import com.interest.user.model.entity.UserDetailEntity;

public interface UserDetailService {

    void updateUserInfo(int userId, String info, String location, String skill);

    void insert(UserDetailEntity userDetailEntity);
}
