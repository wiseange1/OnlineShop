package com.study.OnlineShop.web.auth;

import com.study.OnlineShop.entity.User;

import java.time.LocalDateTime;

public class Session {
    private String token;
    private User user;
    private LocalDateTime expireDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

}
