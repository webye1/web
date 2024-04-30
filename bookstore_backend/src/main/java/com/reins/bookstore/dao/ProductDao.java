package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProduct();

    Product getOneProduct(Integer id);

    Product insertOneProduct(Product product);

    Product updateOneProduct(Product product);

    void deleteOneProduct(Integer id);

    Product getOneProduct(String trackingNumber);

    List<Product> getOneProductbyc(String counterNumber);

    String insertall(List<Product> productlist);

    int getMaxID();
}
