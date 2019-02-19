package com.study.OnlineShop.service.impl;

import com.study.OnlineShop.entity.User;
import com.study.OnlineShop.entity.UserRole;
import com.study.OnlineShop.service.SecurityService;
import com.study.OnlineShop.service.UserService;
import com.study.OnlineShop.web.auth.AuthUtils;
import com.study.OnlineShop.web.auth.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultSecurityService implements SecurityService {

    private List<Session> sessionList = Collections.synchronizedList(new ArrayList<>());
    private UserService userService;

    @Override
    public Session login (String login, String password) {
        //check user
        User user = userService.getByLogin(login);
        //create session
        if (user != null && password.equals(user.getSole())) {  // Authentification
            if (UserRole.getByLogin(user.getRole()) == UserRole.ADMIN) { // Authorization as Admin
                String token = AuthUtils.addNewToken();
                Session session = new Session();
                session.setToken(token);
                session.setUser(user);
                session.setExpireDate(LocalDateTime.now().plusHours(2));
                sessionList.add(session);
                return session;
            }
        }
        return null;
    }

    @Override
    public Session findByToken(String token) {
        return null;
    }

    @Override
    public void removeByToken(String token) {

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
