package com.jwt.demo.controller;

import com.jwt.demo.exception.AuthenticationException;
import com.jwt.demo.model.User;
import com.jwt.demo.security.TokenUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {
    /***
     * 获取令牌
     *
     * */
    @PostMapping("/tokens")
    public String getToken(
            @RequestParam String username,
            @RequestParam String password
    ) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //模拟数据库操作,核对账号密码,不是账号admin或者user
        if (!"admin".equals(user.getUsername()) && !"user".equals(user.getUsername())) throw new AuthenticationException("账号或密码不正确");
        //模拟数据库操作,获取权限集
//        List<String> authKeys = new ArrayList<>();
//        if ("admin".equals(user.getUsername())) {
//            authKeys.add("ROLE_ADMIN");
//        }
//        if ("user".equals(user.getUsername())) {
//            authKeys.add("ROLE_USER");
//        }
//        user.setAuthKeys(authKeys);
        String token = generateToken(user);
        return token;
    }

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    private String generateToken(User user) {
        Map<String, String> data = new HashMap<>();
        data.put("username", user.getUsername());
        //可以选择将权限放在token里，在过滤器里取出来
        //自己新建jwtUser对象，而不用每次通过userService从数据库中获取。
        // String authKeys = JSON.toJSONString(user.getAuthKeys());
        // data.put("authKeys", authKeys);
        return TokenUtil.createToken(data);
    }
}
