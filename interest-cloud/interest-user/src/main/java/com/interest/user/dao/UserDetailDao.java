package com.interest.user.dao;

import com.interest.user.model.entity.UserDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDetailDao {

    void updateUserInfo(@Param("userId")int userId, @Param("info") String info, @Param("location") String location, @Param("skill") String skill);

    void insert(UserDetailEntity userDetailEntity);
}
