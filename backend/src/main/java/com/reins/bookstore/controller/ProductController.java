package com.reins.bookstore.controller;

import com.alibaba.fastjson.JSONObject;

import com.reins.bookstore.entity.Product;

import com.reins.bookstore.service.ProductService;
import com.reins.bookstore.service.RecordService;
import com.reins.bookstore.utils.Message;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;


import static com.reins.bookstore.utils.Adapter.wrapProduct;

@RestController
@CrossOrigin
@RequestMapping("/Product")
public class ProductController {
    ProductService productService;
    RecordService recordService;
    public ProductController(ProductService productService,RecordService recordService) {
        this.productService = productService;
        this.recordService = recordService;
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

    @RequestMapping("/update")
    public List<JSONObject> updateProduct(@RequestBody JSONObject jsonObject
    ) {

        List<JSONObject> results = new ArrayList<>();
        String counter_number = jsonObject.getString("counter_number");
        String state = jsonObject.getString("state");
        String op_time = jsonObject.getString("op_time");
        List<Product> plist = productService.getOneProductbyc(counter_number);
        for (Product it :plist) {

            it.setOptime(op_time);
            it.setState(state);
            JSONObject res = new JSONObject();

            res = updateOneProduct(wrapProduct(it));
            if (res.getInteger("product_id") == -1){
                results.add(res);
                System.out.println("-1");
            }else{

                results.add(res);
                System.out.println("ok");


            }
        }





        return results;

    }


    @RequestMapping("/insert")
    public List<String> insertAllProduct(@RequestBody List<JSONObject> jsonObjectList
    ) {
        List<String> results = new ArrayList<>();
        for (JSONObject jsonObject :jsonObjectList){

            String tracking_number = jsonObject.getString("tracking_number");
            Product p = productService.getOneProduct(tracking_number);

            JSONObject res = new JSONObject();
            int i = -1;
            if (p != null ) {//存在
                res = updateOneProduct(jsonObject);
                i = 0;
            }
            else {
                res = insertOneProduct(jsonObject);
                i = 1;
            }
            System.out.println("json"+res);
            System.out.println("pid"+(res.getInteger("product_id")));

            if (res.getInteger("product_id") == -1){
                results.add("Product Error!");
                System.out.println("-1");
            }else{
                if (i==0){
                    results.add("Product Update!");
                    System.out.println("ok");
                }
                if(i == 1){
                    results.add("Product Insert!");
                    System.out.println("ok");
                }

            }

        }

        return results;

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
        Product p = new Product();
        //去focus表中找id，有的话继续，没有的话返回
        System.out.println("cn:"+counter_number);

        p = productService.getOneProduct(tracking_number);


        if (p == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Not found this product.");
            System.out.println("Not found this product");
            return wrapProduct(p);
        }

        Product p1 = productService.getOneProduct(p.getProductId());
        if (p1 == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("Not found this product id");
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
        product.setStoreroomtime(p.getStoreroomtime());
        product.setFinishtime(p.getFinishtime());
        product.setClearancetime(p.getClearancetime());
        product.setTrucktime((p.getTrucktime()));
        product.setStationtime((p.getStationtime()));
        product.setPorttime(p.getPorttime());
        product.setCompartmenttime(p.getCompartmenttime());


        if (state == null ){
            product.setState(p.getState());
            System.out.println("statenull:"+state);
            System.out.println("pstatenull:"+p.getState());
        }else{
            System.out.println("statenotnull:"+state);
            System.out.println("pstatenotnull:"+p.getState());


                System.out.println("statenote:"+state);
                System.out.println("pstatenote:"+p.getState());
                if (state.equals("已装柜")){
                    product.setCompartmenttime(op_time);
                } else if (state.equals("已入库")) {
                    product.setStoreroomtime(op_time);
                } else if (state.equals("已到港")) {
                    product.setPorttime(op_time);
                } else if (state.equals("已到站")) {
                    product.setStationtime(op_time);
                } else if (state.equals("清关中")) {
                    product.setClearancetime(op_time);
                } else if (state.equals("已装车")) {
                    product.setTrucktime(op_time);
                } else if (state.equals("已完成")) {
                    product.setFinishtime(op_time);
                    product.setCounternumber(null);//完成的柜号清空
                }

            product.setState(state);

        }

        System.out.println("productpostupdate");
        Product product1 = productService.updateOneProduct(product);
        System.out.println(product1);
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
