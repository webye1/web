package com.reins.bookstore.controller;

import com.alibaba.fastjson.JSONObject;

import com.reins.bookstore.entity.Product;

import com.reins.bookstore.repository.ProductRepository;
import com.reins.bookstore.service.ProductService;
import com.reins.bookstore.service.RecordService;
import com.reins.bookstore.utils.Message;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


import static com.reins.bookstore.utils.Adapter.js2product;
import static com.reins.bookstore.utils.Adapter.wrapProduct;
import static java.lang.Math.min;
import static jdk.nashorn.internal.objects.NativeMath.max;

@RestController
@CrossOrigin
@RequestMapping("/Product")
public class ProductController {
    ProductService productService;
    ProductRepository productRepository;
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




    @Scheduled(cron = "0 15 10 ? * MON")
    private void deleteProcudt(){
        List<Product> products = productService.getAllProduct();
        for(Product it :products){
            if(it.getState().equals("已完成")) {

                String time = it.getFinishtime();
                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                Date date = null;
                try {
                    date = sdf.parse(time);
                    Date currentDate = new Date();
                    System.out.println("String类型的时间转换为Date类型成功：" + date);
                    System.out.println("当前时间：" + currentDate);
                    long days = ChronoUnit.DAYS.between(date.toInstant(), currentDate.toInstant());
                    System.out.println("差：" + days);
                    if (days >= 180) {
                        deleteOneProduct(it.getTrackingnumber());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @RequestMapping("/getone")
    public JSONObject getOnebyTn( @RequestParam(value = "tracking_number",required = false) String tracking_number
    ) {
        Product product = productService.getOneProduct(tracking_number);
        return wrapProduct(product);
    }
    @RequestMapping("/update")
    public  List<JSONObject> updateProduct(@RequestBody JSONObject jsonObject
    ) {

        List<JSONObject> results = new ArrayList<>();
        String counter_number = jsonObject.getString("counter_number");
        String state = jsonObject.getString("state");
        String op_time = jsonObject.getString("op_time");
        List<Product> plist = productService.getOneProductbyc(counter_number);
        List<Product> productlist = new ArrayList<>();
        int num= plist.size();
        int i = 0;
        String s = "";
        while(num-1000*i>0){
            for(int j=i*1000;j<min(i*1000+1000,num);j++){

                Product p = plist.get(j);
                String product_name = jsonObject.getString("product_name");
                String proxy = jsonObject.getString("proxy");
                //String counter_number = jsonObject.getString("counter_number");
                String tracking_number = jsonObject.getString("tracking_number");
                String customer = jsonObject.getString("customer");
                Integer count = jsonObject.getInteger("count");
                Float weight = jsonObject.getFloat("weight");
                //String state = jsonObject.getString("state");
                //String op_time = jsonObject.getString("op_time");

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
                product.setWarehousetime(p.getWarehousetime());

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
                    } else if (state.equals("已到仓")) {
                        product.setWarehousetime(op_time);
                    } else if (state.equals("已完成")) {
                        product.setFinishtime(op_time);
                        product.setCounternumber(null);//完成的柜号清空
                    }

                    product.setState(state);

                }
                if (product.getTrackingnumber()!=null&&product.getTrackingnumber()!=""){
                    productlist.add(product);
                    results.add(wrapProduct(product));
                }

            }

            s=  productService.insertall(productlist);
            i=i+1;
            productlist.clear();
        }

        /*
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
        */

        return results;

    }


    @RequestMapping("/insert")
    public List<String> insertAllProduct(@RequestBody List<JSONObject> jsonObjectList
    ) {
        List<String> results = new ArrayList<>();

        int max_id = productService.getMaxID();
        int num= jsonObjectList.size();
        System.out.println("js_size:"+num);
        int i = 0;
        List<Product> productlist = new ArrayList<>();
        String s = "";
        List<Product> psearch = new ArrayList<>();
        List<Integer> p_state = new ArrayList<>();
        for(JSONObject jsonObject:jsonObjectList){

            if (jsonObject.getString("tracking_number")!=null && jsonObject.getString("tracking_number")!=""){
                Product p =  productService.getOneProduct(jsonObject.getString("tracking_number"));

                if (p == null){//插入
                    p_state.add(0);
                    psearch.add(new Product());
                }else{//更新
                    p_state.add(1);
                    psearch.add(p);
                }
            }else{

                p_state.add(-1);
                psearch.add(new Product());
            }
        }
        while(num-1000*i>0){

            for(int j=i*1000;j<min(i*1000+1000,num);j++){

                JSONObject jsonObject = jsonObjectList.get(j);
                if (jsonObject.getString("tracking_number")!=null && jsonObject.getString("tracking_number")!="")
                {

                    //Product p = productService.getOneProduct(jsonObject.getString("tracking_number"));
                    Product product = new Product();
                    if(p_state.get(j) == 0){//插入

                        product = js2product(jsonObject);
                        product.setProductId(++max_id);
                    }else{//更新
                        Product p = psearch.get(j);
                        String product_name = jsonObject.getString("product_name");
                        String proxy = jsonObject.getString("proxy");
                        String counter_number = jsonObject.getString("counter_number");
                        String tracking_number = jsonObject.getString("tracking_number");
                        String customer = jsonObject.getString("customer");
                        Integer count = jsonObject.getInteger("count");
                        Float weight = jsonObject.getFloat("weight");
                        String state = jsonObject.getString("state");
                        String op_time = jsonObject.getString("op_time");


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
                        product.setWarehousetime(p.getWarehousetime());

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
                            } else if (state.equals("已到仓")) {
                                product.setWarehousetime(op_time);
                            } else if (state.equals("已完成")) {
                                product.setFinishtime(op_time);
                                product.setCounternumber(null);//完成的柜号清空
                            }

                            product.setState(state);

                        }
                    }

                    if (product.getTrackingnumber()!=null&&product.getTrackingnumber()!=""){
                        productlist.add(product);
                        results.add("Insert all product!");
                    }

                }
            }
            s=  productService.insertall(productlist);
            i=i+1;
            productlist.clear();
        }
        /*
        List<Product> productlist = new ArrayList<>();
        for (JSONObject jsonObject :jsonObjectList){
            if (jsonObject.getString("tracking_number")!=null && jsonObject.getString("tracking_number")!="")
            {
                Product p1 = js2product(jsonObject);
                if (p1.getTrackingnumber()!=null&&p1.getTrackingnumber()!=""){
                    productlist.add(p1);
                }
            }

        }
        System.out.println(productlist);
        String s =  productService.insertall(productlist);

         */


        /*
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
        */
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
        product.setWarehousetime(p.getWarehousetime());

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
                } else if (state.equals("已到仓")) {
                    product.setWarehousetime(op_time);
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
        //System.out.println("TN:"+p.getTrackingnumber());
        //System.out.println("ID:"+p.getProductId());
        if (!productService.deleteOneProduct(p.getProductId())) {
            System.out.println("not found");
            return new Message(-1, "User not found.");
        } else {
            System.out.println("OK");
            return new Message(0, "OK");
        }
    }
}
