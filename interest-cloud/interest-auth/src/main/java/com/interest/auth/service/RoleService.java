package com.interest.auth.service;

import java.util.List;

public interface RoleService {
    /**
     * 通过UserId得到对应的role
     *
     * @param id
     * @return
     */
    List<String> getRolesByUserId(int id);
}
