package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProduct();

    Product getOneProduct(Integer id);

    Product insertOneProduct(Product product);

    Product updateOneProduct(Product product);

    void deleteOneProduct(int id);

    Product getOneProduct(String trackingNumber);
}
