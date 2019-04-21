package com.interest.auth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDao {

    /**
     * 通过UserId得到对应的role
     *
     * @param userId
     * @return
     */
    List<String> getRolesByUserId(@Param("userId") int userId);
}
