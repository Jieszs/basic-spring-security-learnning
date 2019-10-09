package com.jwt.demo.security;

import com.jwt.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //模拟数据库操作
        if (!"admin".equals(username) && !"user".equals(username))
            throw new UsernameNotFoundException(username + "不存在");

        User user = new User();
        user.setUsername(username);
        //模拟数据库操作,获取权限集
        List<String> authKeys = new ArrayList<>();
        if ("admin".equals(user.getUsername())) {
            authKeys.add("ROLE_ADMIN");
        }
        if ("user".equals(user.getUsername())) {
            authKeys.add("ROLE_USER");
        }
        user.setAuthKeys(authKeys);

        return JwtUserFactory.create(user);

    }
}
