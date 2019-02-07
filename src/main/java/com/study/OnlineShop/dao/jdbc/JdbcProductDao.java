package com.study.OnlineShop.dao.jdbc;

import com.study.OnlineShop.dao.ProductDao;
import com.study.OnlineShop.dao.jdbc.mapper.ProductMapper;
import com.study.OnlineShop.entity.Product;

import java.sql.*;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final String JDBC_CONNECTION = "jdbc:sqlite:C:\\Users\\Tema\\IdeaProjects\\test.db";
    private static final String FIND_ALL_PRODUCTS = "SELECT id, name, price FROM product;";
    private static final String DELETE_BY_ID = "DELETE FROM product WHERE id = ?;";
    private static final String INSERT_PRODUCT = "INSERT INTO product (name, price) VALUES (?, ?);";


    private static final ProductMapper productMapper = new ProductMapper();

    @Override
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

    @Override
    public void deleteById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQLException", e);
        }
    }

    @Override
    public void add(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setDouble(2, product.getPrice());
                preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("SQLException", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_CONNECTION);
    }
}