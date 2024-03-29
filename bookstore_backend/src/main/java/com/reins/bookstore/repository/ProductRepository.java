package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = "select * from product",nativeQuery = true)
    List<Product> getAllProduct();

    @Query(value = "select * from product where tracking_number = ?1",nativeQuery = true)
    Product getOneProduct(String trackingNumber);
}
