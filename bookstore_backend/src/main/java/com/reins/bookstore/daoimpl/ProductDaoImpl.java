package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.ProductDao;
import com.reins.bookstore.entity.Manager;
import com.reins.bookstore.entity.Product;
import com.reins.bookstore.repository.ManagerRepository;
import com.reins.bookstore.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    private final ProductRepository productRepository;

    public ProductDaoImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = productRepository.getAllProduct();
        return products;
    }

    @Override
    public Product getOneProduct(Integer id) {
        System.out.println("productDaogetone");
        Product product = productRepository.getOnePbyid(id);//insertOneFocus(focus.getUserid(),focus.getTrackingnum());
        return product;
    }

    @Override
    public Product insertOneProduct(Product product) {
        System.out.println("productDaoinsert");
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product updateOneProduct(Product product) {
        System.out.println("productDaoupdate");
        Product product1 = productRepository.save(product);//insertOneFocus(focus.getUserid(),focus.getTrackingnum());
        return product1;
    }

    @Override
    public void deleteOneProduct(Integer id) throws IllegalArgumentException{
        System.out.println("repostitory delete");
        productRepository.deleteProductById(id);

    }

    @Override
    public Product getOneProduct(String trackingNumber) {
        Product product = productRepository.getOneProduct(trackingNumber);
        return product;
    }

    @Override
    public List<Product> getOneProductbyc(String counterNumber) {
        List<Product> product = productRepository.getOneProductbyc(counterNumber);
        return product;
    }

    @Override
    public String insertall(List<Product> productlist) {
        productRepository.saveAll(productlist);
        String s= "Insert all product!";
        return s;
    }

    @Override
    public int getMaxID() {
        Integer max_id = productRepository.getMaxID();
        return max_id;
    }
}
