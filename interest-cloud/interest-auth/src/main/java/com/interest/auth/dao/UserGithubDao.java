package com.interest.auth.dao;

import com.interest.auth.model.entity.UserGithubEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserGithubDao {

    void insertEntity(UserGithubEntity userGithubEntity);
}
