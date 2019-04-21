package com.interest.user.dao;

import com.interest.user.model.entity.RelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RelationDao {

    /**
     * 通过userId得到关系List
     *
     * @param userId
     * @return
     */
    List<RelationEntity> getRelationByUserId(@Param("userId") int userId);

    /**
     * 通过userId删除关系
     *
     * @param userId
     */
    void delById(@Param("userId") int userId);

    /**
     * 批量插入关系数据
     *
     * @param relationList
     */
    void insertRelations(List<RelationEntity> relationList);
}
