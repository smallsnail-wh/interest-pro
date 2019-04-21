package com.interest.user.service.impl;

import com.interest.user.dao.RelationDao;
import com.interest.user.model.entity.RelationEntity;
import com.interest.user.service.RelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationDao relationDao;

    @Override
    public List<RelationEntity> getRelationByUserId(int userId) {
        return relationDao.getRelationByUserId(userId);
    }

    @Transactional
    @Override
    public void insertRelations(List<RelationEntity> relationList) {
        if (!CollectionUtils.isEmpty(relationList)) {
            log.info("delete | r_user_role | delete user's roles | userId: {}", relationList.get(0).getUserId());
            relationDao.delById(relationList.get(0).getUserId());
            log.info("insert | r_user_role | insert user's roles | params: {}", relationList);
            relationDao.insertRelations(relationList);
        }
    }

}
