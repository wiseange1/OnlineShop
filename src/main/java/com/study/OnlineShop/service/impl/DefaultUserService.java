package com.study.OnlineShop.service.impl;

import com.study.OnlineShop.dao.UserDao;
import com.study.OnlineShop.entity.User;
import com.study.OnlineShop.service.UserService;

public class DefaultUserService implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }
}
