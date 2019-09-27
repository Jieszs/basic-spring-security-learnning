package com.customlogincaptcha.demo.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码接口
 *
 * 2019/9/26
 */

@RestController
public class CaptchaController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping("/captcha")
    public void captcha() throws Exception {
        //输出验证码
        CaptchaUtil.out(request, response);
    }
}
