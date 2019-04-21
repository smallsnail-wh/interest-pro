package com.interest.auth.service.impl;

import com.interest.auth.dao.UserQQDao;
import com.interest.auth.model.entity.UserQQEntity;
import com.interest.auth.service.UserQQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserQQServiceImpl implements UserQQService {

    @Autowired
    private UserQQDao userQQDao;

    @Override
    public void insertEntity(UserQQEntity userQQEntity) {
        userQQDao.insertEntity(userQQEntity);
    }
}
