package com.interest.user.dao;

import com.interest.common.model.response.UserHeadInfoVO;
import com.interest.user.model.entity.UserEntity;
import com.interest.user.model.response.UserBaseInfoVO;
import com.interest.user.model.response.UserInfoVO;
import com.interest.user.model.response.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserDao {

    UserBaseInfoVO getUserBaseInfoById(@Param("id") int userId);

    List<UserHeadInfoVO> getUsersHeadInfoByIds(@Param("ids") Set<Integer> ids);

    UserHeadInfoVO getUsersHeadInfoById(@Param("id") Integer id);

    UserInfoVO getUserInfoById(@Param("userId") int userId);

    void updateUserInfo(@Param("userId") int userId, @Param("name") String name, @Param("url") String url, @Param("email") String email);

    UserEntity getUserEntityById(@Param("id") Integer id);

    void updateHeadImg(@Param("id") Integer id, @Param("headImg") String headImg);

    List<UserVO> getUsersList(@Param("name") String name, @Param("userId") Integer userId, @Param("status") Integer status, @Param("type") Integer type, @Param("pageSize") int pageSize, @Param("start") int start);

    Integer getUsersSize(@Param("name") String name, @Param("userId") Integer userId, @Param("status") Integer status, @Param("type") Integer type, @Param("pageSize") int pageSize, @Param("start") int start);

    void updateUsersStatus(@Param("groupId") List<String> groupId,@Param("status") Integer status);

    void insertSystemUser(UserEntity userEntity);

    void updateSystemUser(UserEntity userEntity);
}
