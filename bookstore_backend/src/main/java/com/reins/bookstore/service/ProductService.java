package com.reins.bookstore.service;

import com.reins.bookstore.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    Product getOneProduct(Integer id);

    Product insertOneProduct(Product product);

    Product updateOneProduct(Product product);

    boolean deleteOneProduct(int id);

    Product getOneProduct(String trackingNumber);

    List<Product> getOneProductbyc(String counterNumber);

    String insertall(List<Product> productlist);

    int getMaxID();
}
