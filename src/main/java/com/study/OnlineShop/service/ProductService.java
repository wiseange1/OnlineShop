package com.study.OnlineShop.service;

import com.study.OnlineShop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    void add(Product product);

    void deleteById(int id);
}
