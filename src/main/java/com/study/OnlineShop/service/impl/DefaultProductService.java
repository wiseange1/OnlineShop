package com.study.OnlineShop.service.impl;

import com.study.OnlineShop.dao.ProductDao;
import com.study.OnlineShop.entity.Product;
import com.study.OnlineShop.service.ProductService;

import java.util.List;

public class DefaultProductService implements ProductService {

    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public void deleteById(int id) {
        productDao.deleteById(id);
    }

    @Override
    public void add(Product product) {
        productDao.add(product);
    }

    @Override
    public void updateById(Product product) {
        productDao.updateById(product);
    }

    @Override
    public Product getById(int id) {
        return productDao.getById(id);
    }
}
