package com.study.OnlineShop.dao.jdbc;

import com.study.OnlineShop.entity.Product;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcProductDaoITest {
    @Test
    public void testGetAll() {
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        List<Product> list = jdbcProductDao.getAll();
        assertFalse(list.isEmpty());
        for (Product item : list){
            assertNotEquals(item.getId(), 0);
            assertNotNull(item.getName());
            assertNotEquals(item.getPrice(), 0);
        }
    }
}