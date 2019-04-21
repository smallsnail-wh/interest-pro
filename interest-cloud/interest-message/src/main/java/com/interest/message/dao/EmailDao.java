package com.interest.message.dao;

import com.interest.message.model.entity.EmailEntity;
import com.interest.message.model.response.EmailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmailDao {

    void insertEntity(EmailEntity emailEntity);

    List<EmailVO> getEmailsList(@Param("pageSize") int pageSize, @Param("start") int start);

    Integer getEmailsSize(@Param("pageSize") int pageSize, @Param("start") int start);

    void deleteEmails(@Param("groupId") List<String> groupId);
}
