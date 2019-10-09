package com.jwt.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/user")
    public String helloUser() {
        return "Hello User!";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
