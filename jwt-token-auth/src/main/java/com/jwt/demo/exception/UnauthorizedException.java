package com.jwt.demo.exception;

/**
 * Created by zj on 2019/10/9
 */
public class UnauthorizedException extends Exception {
    public UnauthorizedException(String message) {
        super(message);
    }
}
