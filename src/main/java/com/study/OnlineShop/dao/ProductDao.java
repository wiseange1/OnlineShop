package com.study.OnlineShop.dao;

import com.study.OnlineShop.entity.Product;

import java.util.List;

public interface ProductDao {

   List<Product> getAll();

   void add(Product product);

   void deleteById(int id);

}
