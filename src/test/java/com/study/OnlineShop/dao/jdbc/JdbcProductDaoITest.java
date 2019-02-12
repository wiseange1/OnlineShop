package com.study.OnlineShop.dao.jdbc;

import com.study.OnlineShop.dao.jdbc.connection.JDBCConnection;
import com.study.OnlineShop.entity.Product;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class JdbcProductDaoITest {
    @Test
    public void testGetAll() throws SQLException, IOException {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\db.properties");){
            properties.load(fileInputStream);
        }
        JDBCConnection jdbcConnection = new JDBCConnection();
        jdbcConnection.setProperties(properties);
        JdbcProductDao jdbcProductDao = new JdbcProductDao(jdbcConnection);
        List<Product> list = jdbcProductDao.getAll();
        assertFalse(list.isEmpty());
        for (Product item : list){
            assertNotEquals(item.getId(), 0);
            assertNotNull(item.getName());
            assertNotEquals(item.getPrice(), 0);
        }
    }
}