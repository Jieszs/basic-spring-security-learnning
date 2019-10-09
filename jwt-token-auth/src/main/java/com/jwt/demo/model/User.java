package com.jwt.demo.model;

import java.util.List;

public class User {
    private String username;
    private String password;
    private List<String> authKeys;//权限集

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAuthKeys() {
        return authKeys;
    }

    public void setAuthKeys(List<String> authKeys) {
        this.authKeys = authKeys;
    }
}
