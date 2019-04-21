package com.interest.user.service.impl;

import com.interest.user.dao.UserDetailDao;
import com.interest.user.model.entity.UserDetailEntity;
import com.interest.user.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailDao userDetailDao;

    @Override
    public void updateUserInfo(int userId, String info, String location, String skill) {
        userDetailDao.updateUserInfo(userId, info, location, skill);
    }

    @Override
    public void insert(UserDetailEntity userDetailEntity) {
        log.info("insert | user_detail | insert user detail | params: {}", userDetailEntity);
        userDetailDao.insert(userDetailEntity);
    }
}
