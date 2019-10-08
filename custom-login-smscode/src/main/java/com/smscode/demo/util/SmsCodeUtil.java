package com.smscode.demo.util;

import java.time.LocalDateTime;
import java.util.Random;

public class SmsCodeUtil {
    private String code;
    private LocalDateTime expireTime;

    public SmsCodeUtil(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public SmsCodeUtil(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public static SmsCodeUtil createSMSCode() {
        Random random = new Random();
        Integer code = random.nextInt(1000000);
        return new SmsCodeUtil(code.toString(), 60);
    }
}
