package com.interest.auth.service.impl;

import com.interest.auth.dao.RoleDao;
import com.interest.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<String> getRolesByUserId(int id) {
        return roleDao.getRolesByUserId(id);
    }
}
