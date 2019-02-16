package com.study.OnlineShop.dao;

import com.study.OnlineShop.entity.User;

public interface UserDao {

    void addUser(User user);

    User getByLogin(String login);
}
