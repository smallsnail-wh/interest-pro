package com.interest.auth.service.impl;

import com.interest.auth.dao.UserDetailDao;
import com.interest.auth.model.entity.UserDetailEntity;
import com.interest.auth.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailDao userDetailDao;

    @Override
    public void insert(UserDetailEntity userDetailEntity) {
        userDetailDao.insert(userDetailEntity);
    }
}
