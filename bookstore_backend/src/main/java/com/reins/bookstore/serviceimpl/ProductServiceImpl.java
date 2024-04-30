package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.ManagerDao;
import com.reins.bookstore.dao.ProductDao;
import com.reins.bookstore.entity.Manager;
import com.reins.bookstore.entity.Product;
import com.reins.bookstore.service.ProductService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"lastResult"})
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = productDao.getAllProduct();
        return products;
    }

    @Override
    public Product getOneProduct(Integer id) {
        System.out.println("productservicegetone");
        Product product = productDao.getOneProduct(id);
        return product;
    }

    @Override
    public Product insertOneProduct(Product product) {
        System.out.println("productserviceinsert");
        Product product1 = productDao.insertOneProduct(product);
        return product1;
    }

    @Override
    public Product updateOneProduct(Product product) {
        System.out.println("productserviceupdate");
        Product product1 = productDao.updateOneProduct(product);
        return product1;
    }

    @Override
    public boolean deleteOneProduct(Integer id) {
        System.out.println("productservicedeleteone");
        try {
            productDao.deleteOneProduct(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Product getOneProduct(String trackingNumber) {
        System.out.println("productservicegetoneby");
        Product product = productDao.getOneProduct(trackingNumber);
        return product;
    }

    @Override
    public List<Product> getOneProductbyc(String counterNumber) {
        List<Product> product = productDao.getOneProductbyc(counterNumber);
        return product;
    }

    @Override
    public String insertall(List<Product> productlist) {
        String s = productDao.insertall(productlist);
        return s;
    }

    @Override
    public int getMaxID() {
        int max_int = productDao.getMaxID();
        return max_int;
    }


}
