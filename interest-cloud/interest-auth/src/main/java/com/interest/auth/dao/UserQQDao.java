package com.interest.auth.dao;

import com.interest.auth.model.entity.UserQQEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserQQDao {

    void insertEntity(UserQQEntity userQQEntity);
}
