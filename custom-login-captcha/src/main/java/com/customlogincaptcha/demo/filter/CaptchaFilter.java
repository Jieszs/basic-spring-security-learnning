package com.customlogincaptcha.demo.filter;


import com.customlogincaptcha.demo.exception.CaptchaException;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 *
 * 2019/9/26
 */

@Component
public class CaptchaFilter extends OncePerRequestFilter {
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //登录，即，post方法请求/login
        if (request.getRequestURI().equalsIgnoreCase("/login") && request.getMethod().equalsIgnoreCase("post")) {
            //获取验证码,请求参数名为captcha
            String captcha = request.getParameter("captcha");
            //验证不通过，从session中清空验证码，调用Spring Security的校验失败处理器AuthenticationFailureHandler进行处理。
            if (!CaptchaUtil.ver(captcha, request)) {
                CaptchaUtil.clear(request);
                authenticationFailureHandler.onAuthenticationFailure(request, response, new CaptchaException("验证码不正确"));
                return;
            }
            //验证通过，从session中清空验证码
            CaptchaUtil.clear(request);
        }
        filterChain.doFilter(request, response);
    }
}
