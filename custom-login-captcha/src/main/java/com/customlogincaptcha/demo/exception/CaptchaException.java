package com.customlogincaptcha.demo.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 * <p>
 * 2019/9/26
 */

public class CaptchaException extends AuthenticationException {
    public CaptchaException(String message) {
        super(message);
    }
}
