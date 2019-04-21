package com.interest.auth.service.impl;

import com.interest.auth.dao.UserGithubDao;
import com.interest.auth.model.entity.UserGithubEntity;
import com.interest.auth.service.UserGithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGithubServiceImpl implements UserGithubService {

    @Autowired
    private UserGithubDao userGithubDao;

    @Override
    public void insertEntity(UserGithubEntity userGithubEntity) {
        userGithubDao.insertEntity(userGithubEntity);
    }
}
