package com.reins.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.reins.bookstore.entity.Manager;
import com.reins.bookstore.entity.Product;
import com.reins.bookstore.service.ManagerService;
import com.reins.bookstore.service.ProductService;
import com.reins.bookstore.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.reins.bookstore.utils.Adapter.wrapManager;
import static com.reins.bookstore.utils.Adapter.wrapProduct;

@RestController
@CrossOrigin
@RequestMapping("/Product")
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/getall")
    public List<JSONObject> getAllProduct() {
        System.out.println("productmanager");
        List<Product> products = productService.getAllProduct();
        List<JSONObject> resData = new ArrayList<>();
        for (Product t : products) {
            resData.add(wrapProduct(t));
        }
        for (JSONObject j : resData) {
            System.out.println(j);
        }
        System.out.println(resData);
        return resData;
    }

    @RequestMapping("/{id}")
    public JSONObject getOneProduct(@PathVariable Integer id) {
        System.out.println("getoneproduct");
        System.out.println(id);
        Product product = productService.getOneProduct(id);
        return wrapProduct(product);
    }

    @PostMapping
    public JSONObject insertOneProduct(@RequestBody JSONObject jsonObject
    ) {

        String product_name = jsonObject.getString("product_name");
        String proxy = jsonObject.getString("proxy");
        String counter_number = jsonObject.getString("counter_number");
        String tracking_number = jsonObject.getString("tracking_number");
        String customer = jsonObject.getString("customer");
        Integer count = jsonObject.getInteger("count");
        Float weight = jsonObject.getFloat("weight");
        //String state = jsonObject.getString("state");
        String op_time = jsonObject.getString("op_time");

        Product p = productService.getOneProduct(tracking_number);
        if (p != null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("trackingnumber exist");
            return wrapProduct(null);
        }

        //去user表中找是否有该userid，有的话继续，没的话返回
        Product product = new Product();

        product.setProductname(product_name);
        product.setProxy(proxy);
        product.setCounternumber(counter_number);
        product.setTrackingnumber(tracking_number);
        product.setCustomer(customer);
        product.setCount(count);
        product.setWeight(weight);
        product.setState("已入库");
        product.setOptime(op_time);
        product.setStoreroomtime(op_time);
        System.out.println("productpostinsert");
        Product product1 = productService.insertOneProduct(product);
        System.out.println(product1);
        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("message", "Access denied.");
        return wrapProduct(product1);
    }

    @PutMapping
    public JSONObject updateOneProduct(@RequestBody JSONObject jsonObject
    ) {

        String product_name = jsonObject.getString("product_name");
        String proxy = jsonObject.getString("proxy");
        String counter_number = jsonObject.getString("counter_number");
        String tracking_number = jsonObject.getString("tracking_number");
        String customer = jsonObject.getString("customer");
        Integer count = jsonObject.getInteger("count");
        Float weight = jsonObject.getFloat("weight");
        String state = jsonObject.getString("state");
        String op_time = jsonObject.getString("op_time");

        //去focus表中找id，有的话继续，没有的话返回

        System.out.println("cn:"+counter_number+"tn:"+tracking_number);
        Product p = productService.getOneProduct(tracking_number);
        if (p == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("pgetonenull");
            return wrapProduct(p);
        }

        Product p1 = productService.getOneProduct(p.getProductId());
        if (p1 == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("p1getonenull");
            return wrapProduct(p1);
        }
        Product product = new Product();
        product.setProductId(p.getProductId());
        if (product_name == null ){
            product.setProductname(p.getProductname());
        }else{
            product.setProductname(product_name);
        }
        if (proxy == null ){
            product.setProxy(p.getProxy());
        }else{
            product.setProxy(proxy);
        }
        if (counter_number == null ){
            product.setCounternumber(p.getCounternumber());
        }else{
            product.setCounternumber(counter_number);
        }
        if (tracking_number == null ){
            product.setTrackingnumber(p.getTrackingnumber());
        }else{
            product.setTrackingnumber(tracking_number);
        }
        if (customer == null ){
            product.setCustomer(p.getCustomer());
        }else{
            product.setCustomer(customer);
        }
        if (count == null ){
            product.setCount(p.getCount());
        }else{
            product.setCount(count);
        }
        if (weight == null ){
            product.setWeight(p.getWeight());
        }else{
            product.setWeight(weight);
        }

        if (op_time == null ){
            product.setOptime(p.getOptime());
        }else{
            product.setOptime(op_time);
        }

        if (state == null ){
            product.setState(p.getState());
            System.out.println("statenull:"+state);
            System.out.println("pstatenull:"+p.getState());
        }else{
            if (!state.equals(p.getState())){//状态变

                System.out.println("statenull:"+state);
                System.out.println("pstatenull:"+p.getState());
                if (state.equals("已装柜")){
                    product.setCompartmenttime(op_time);
                } else if (state.equals("已到港")) {
                    product.setPorttime(op_time);
                } else if (state.equals("已到站")) {
                    product.setStationtime(op_time);
                } else if (state.equals("已清关")) {
                    product.setClearancetime(op_time);
                } else if (state.equals("已装车")) {
                    product.setTrucktime(op_time);
                } else if (state.equals("已完成")) {
                    product.setFinishtime(op_time);
                }
            }
            product.setState(state);

        }

        System.out.println("productpostupdate");
        Product product1 = productService.updateOneProduct(product);
        System.out.println(product1);
        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("message", "Access denied.");
        return wrapProduct(product1);
    }

    @DeleteMapping
    public Object deleteOneProduct(@RequestParam(value = "tracking_number",required = false) String tracking_number
    ) {
        System.out.println("productdeleteone");
        Product p = productService.getOneProduct(tracking_number);
        if (p == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("trackingnumber exist");
            return wrapProduct(null);
        }
        if (!productService.deleteOneProduct(p.getProductId())) {
            System.out.println("not found");
            return new Message(-1, "User not found.");
        } else {
            System.out.println("OK");
            return new Message(0, "OK");
        }
    }
}
