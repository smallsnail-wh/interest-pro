package com.interest.user.service.impl;

import com.interest.user.dao.UserGithubDao;
import com.interest.user.service.UserGithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGithubServiceImpl implements UserGithubService {

    @Autowired
    private UserGithubDao userGithubDao;

}
