package com.study.OnlineShop.dao.jdbc.mapper;

import com.study.OnlineShop.entity.Product;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductMapperITest {
    @Test
    public void testMapRaw() throws SQLException {
    ProductMapper productMapper = new ProductMapper();

    ResultSet mockResultSet = mock(ResultSet.class);

    when(mockResultSet.getInt("id")).thenReturn(1);
    when(mockResultSet.getString("name")).thenReturn("Test");
    when(mockResultSet.getDouble("price")).thenReturn(100.00);

    Product testProduct = ProductMapper.mapRaw(mockResultSet);

    assertEquals(1, testProduct.getId());
    assertEquals("Test", testProduct.getName());
    assertEquals(100.00, testProduct.getPrice(), 0.001);

    }
}