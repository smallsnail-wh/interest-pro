package com.interest.auth.dao;

import com.interest.auth.model.entity.UserDetailEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDetailDao {

    void insert(UserDetailEntity userDetailEntity);

}
