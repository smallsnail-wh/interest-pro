package com.interest.message.service.impl;

import com.interest.common.utils.DateUtil;
import com.interest.message.dao.EmailDao;
import com.interest.message.model.entity.EmailEntity;
import com.interest.message.model.response.EmailVO;
import com.interest.message.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailDao emailDao;

    @Override
    public void insertEntity(EmailEntity emailEntity) {
        emailEntity.setCreateTime(DateUtil.currentTimestamp());
        emailDao.insertEntity(emailEntity);
    }

    @Override
    public List<EmailVO> getEmailsList(int pageSize, int start) {
        return emailDao.getEmailsList(pageSize,start);
    }

    @Override
    public Integer getEmailsSize(int pageSize, int start) {
        return emailDao.getEmailsSize(pageSize,start);
    }

    @Override
    public void deleteEmails(List<String> groupId) {
        emailDao.deleteEmails(groupId);
    }

}
