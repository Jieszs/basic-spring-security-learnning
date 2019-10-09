package com.jwt.demo.security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zj on 2019/9/18
 * 过滤器的异常工具类
 */
public class FilterExceptionUtil {

    /**
     * 发送过滤器的异常给前端
     *
     * @param response 响应报文头
     * @param msg      发送的消息
     * @param status   状态
     */
    public static void exceptionResponse(HttpServletResponse response, String msg, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        String resp = getExceptionResponse(msg, status);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(resp);
    }

    /**
     * 报文的包装
     *
     * @param msg    发送的消息
     * @param status 状态
     */
    public static String getExceptionResponse(String msg, HttpStatus status) {
        JSONObject body = new JSONObject();
        body.put("status", status.value());
        body.put("error", status);
        body.put("message", msg);
        return body.toJSONString();
    }
}
