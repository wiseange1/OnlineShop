package com.study.OnlineShop.dao.jdbc;

import com.study.OnlineShop.dao.UserDao;
import com.study.OnlineShop.dao.jdbc.connection.JDBCConnection;
import com.study.OnlineShop.dao.jdbc.mapper.ProductMapper;
import com.study.OnlineShop.entity.Product;
import com.study.OnlineShop.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDao implements UserDao {

    private static final String FIND_BY_LOGIN = "SELECT id, login, role, sole FROM users WHERE login = ?;";

    private final JDBCConnection jdbcConnection ;

    public JdbcUserDao(JDBCConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }


    @Override
    public void addUser(User user) {

    }

    @Override
    public User getByLogin(String login) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {

                User user = new User();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setRole(resultSet.getString("role"));
                    user.setSole(resultSet.getString("sole"));
                }
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL Connection Exception", e);
        }

    }
}
