package com.study.OnlineShop.dao.jdbc.mapper;

import com.study.OnlineShop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static Product mapRaw(ResultSet resultset) throws SQLException {
        Product product = new Product();
        product.setId(resultset.getInt("id"));
        product.setName(resultset.getString("name"));
        product.setPrice(resultset.getDouble("price"));

        return product;
    }

    public static List<Product> mapList(ResultSet resultSet) throws SQLException {

        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = mapRaw(resultSet);
            products.add(product);
        }
        return products;
    }

}
