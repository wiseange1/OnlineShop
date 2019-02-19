package com.study.OnlineShop.service;

import com.study.OnlineShop.web.auth.Session;

public interface SecurityService {

    Session login(String name, String password);

    Session findByToken(String token);

    void removeByToken(String token);
}
