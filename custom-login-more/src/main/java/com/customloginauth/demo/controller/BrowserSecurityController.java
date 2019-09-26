package com.customloginauth.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;
    // Spring Security Web认证机制(通常指表单登录)中登录成功后页面需要跳转到原来客户请求的URL。
    // 该过程中首先需要将原来的客户请求缓存下来，然后登录成功后将缓存的请求从缓存中提取出来。
    // 通过调用它的getRequest方法可以获取到本次请求的HTTP信息
    private RequestCache requestCache = new HttpSessionRequestCache();
    //DefaultRedirectStrategy的sendRedirect为Spring Security提供的用于处理重定向的方法。
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @GetMapping("/auth")
    public String requireAuthentication() throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html"))
                redirectStrategy.sendRedirect(request, response, "/login.html");
        }
        return "访问的资源需要身份认证！";
    }

    @GetMapping("/test")
    public Authentication print()  {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

