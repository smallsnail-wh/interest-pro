package com.interest.auth.dao;

import com.interest.auth.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    UserEntity getUserEntityById(@Param("id") Integer id);

    /**
     * 通过登录名拿到用户信息
     *
     * @param loginName
     * @return
     */
    UserEntity getUserEntityByLoginName(@Param("loginName") String loginName);

    UserEntity getEntityByGithubId(@Param("githubId") String login);

    /**
     * 新建用户信息
     *
     * @param userEntity
     */
    void insertUser(UserEntity userEntity);

    void updateHeadImg(@Param("id") Integer id, @Param("headImg") String headImg);

    UserEntity getEntityByQqid(@Param("qqid") String openid);

    void insertUserByQq(UserEntity userEntity);
}
