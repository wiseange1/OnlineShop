package com.study.OnlineShop.dao.jdbc;

import com.study.OnlineShop.dao.UserDao;
import com.study.OnlineShop.dao.jdbc.connection.JDBCConnection;
import com.study.OnlineShop.entity.User;

public class JdbcUserDao implements UserDao {

    private final JDBCConnection jdbcConnection ;

    public JdbcUserDao(JDBCConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }


    @Override
    public void addUser(User user) {

    }

    @Override
    public User getByLogin(String login) {
        return null;
    }
}
