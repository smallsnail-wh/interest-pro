package com.interest.auth.service.impl;

import com.interest.auth.dao.UserDao;
import com.interest.auth.model.entity.UserEntity;
import com.interest.auth.picture.PictureService;
import com.interest.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private PictureService pictureService;

    @Override
    public UserEntity getUserEntityById(Integer id) {
        return userDao.getUserEntityById(id);
    }

    @Override
    public UserEntity getUserEntityByLoginName(String id) {
        return userDao.getUserEntityByLoginName(id);
    }

    @Override
    public UserEntity getEntityByGithubId(String login) {
        return userDao.getEntityByGithubId(login);
    }

    @Override
    public void insertUser(UserEntity userEntity) {
        userDao.insertUser(userEntity);
    }

    @Override
    public void updateUserHeadImg(int userId, String headImg) {
        userDao.updateHeadImg(userId, headImg);
    }

    @Override
    public UserEntity getEntityByQqid(String openid) {
        return userDao.getEntityByQqid(openid);
    }

    @Override
    public void insertUserByQq(UserEntity userEntity) {
        userDao.insertUserByQq(userEntity);
    }
}
