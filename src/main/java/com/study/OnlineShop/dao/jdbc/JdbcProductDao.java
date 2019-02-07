package com.study.OnlineShop.dao.jdbc;

import com.study.OnlineShop.dao.ProductDao;
import com.study.OnlineShop.dao.jdbc.mapper.ProductMapper;
import com.study.OnlineShop.entity.Product;

import java.sql.*;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final String JDBC_CONNECTION = "jdbc:sqlite:C:\\Users\\Tema\\IdeaProjects\\test.db";
    private static final String FIND_ALL_PRODUCTS = "SELECT id, name, price from product;";

    private static final ProductMapper productMapper = new ProductMapper();

    public List<Product> getAll() {
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_PRODUCTS)) {

            return ProductMapper.mapList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get Products (SQLException)", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_CONNECTION);
    }
}
