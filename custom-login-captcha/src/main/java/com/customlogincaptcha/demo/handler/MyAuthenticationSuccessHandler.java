package com.customlogincaptcha.demo.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
   // private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        //登录成功后页面将跳转回引发跳转的页面。
        // SavedRequest savedRequest = requestCache.getRequest(request, response);
        // redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        //指定跳转的页面
        redirectStrategy.sendRedirect(request, response, "/test");
    }
}
