package com.interest.message.service;

import com.interest.message.model.entity.EmailEntity;
import com.interest.message.model.response.EmailVO;

import java.util.List;

public interface EmailService {

    void insertEntity(EmailEntity emailEntity);

    List<EmailVO> getEmailsList(int pageSize, int start);

    Integer getEmailsSize(int pageSize, int start);

    void deleteEmails(List<String> groupId);
}
