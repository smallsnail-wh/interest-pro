package com.interest.auth.security;

import com.interest.auth.model.entity.UserEntity;
import com.interest.auth.service.RoleService;
import com.interest.auth.service.UserService;
import com.interest.common.utils.MyStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.resource.UserApprovalRequiredException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private final static String DEFAULT_PASSWORD = "interest";

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        UserEntity userEntity = null;

        if (MyStringUtil.isInteger(id)) {
            userEntity = userService.getUserEntityById(Integer.valueOf(id));
        } else {
            userEntity = userService.getUserEntityByLoginName(id);
        }

        if (userEntity == null) {
            throw new UsernameNotFoundException("用户:" + id + "不存在！");
        }
        if(userEntity.getStatus() == 1){
            throw new UserDeniedAuthorizationException("用户:" + id + "禁止登陆！");
        }
        String password = userEntity.getPassword();

        if (password == null) {
            password = DEFAULT_PASSWORD;
        }

        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();

        Iterator<String> iterator = roleService.getRolesByUserId(userEntity.getId()).iterator();
        while (iterator.hasNext()) {
            collection.add(new SimpleGrantedAuthority(iterator.next()));
        }

        return new User(String.valueOf(userEntity.getId()), password, collection);
    }

}
