package com.study.OnlineShop.service;

import com.study.OnlineShop.entity.User;

public interface UserService {
    void addUser(User user);

    User getByLogin (String login);
}
