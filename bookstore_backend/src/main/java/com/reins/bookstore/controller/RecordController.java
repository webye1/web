package com.reins.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.reins.bookstore.entity.Manager;
import com.reins.bookstore.entity.Product;
import com.reins.bookstore.entity.Record;
import com.reins.bookstore.entity.Wxuser;
import com.reins.bookstore.service.ManagerService;
import com.reins.bookstore.service.ProductService;
import com.reins.bookstore.service.RecordService;
import com.reins.bookstore.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.reins.bookstore.utils.Adapter.*;

@RestController
@CrossOrigin
@RequestMapping("/Record")
public class RecordController {
    RecordService recordService;
    ProductService productService;
    ManagerService managerService;
    public RecordController( RecordService recordService,ProductService productService,ManagerService managerService) {
        this.recordService = recordService;
        this.productService = productService;
        this.managerService = managerService;
    }
    @RequestMapping("/getall")
    public List<JSONObject> getAllRecord() {
        System.out.println("recordmanager");
        List<Record> records = recordService.getAllRecord();
        List<JSONObject> resData = new ArrayList<>();
        for (Record t : records) {
            resData.add(wrapRecord(t));
        }
        for (JSONObject j : resData) {
            System.out.println(j);
        }
        System.out.println(resData);
        return resData;
    }

    @RequestMapping("/{id}")
    public JSONObject getOneRecord(@PathVariable Integer id) {
        System.out.println("getonerecord");
        System.out.println(id);
        Record record = recordService.getOneRecord(id);
        return wrapRecord(record);
    }

    @PostMapping
    public JSONObject insertOneRecord(@RequestBody JSONObject jsonObject

    ) {
        String counter_number = jsonObject.getString("counter_number");
        String tracking_number = jsonObject.getString("tracking_number");
        String time_operating = jsonObject.getString("time_operating");
        String manager = jsonObject.getString("manager");
        String update_state = jsonObject.getString("update_state");
        Product product = productService.getOneProduct(tracking_number);
        System.out.println(product);
        if (product == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("can not find product");
            return wrapRecord(null);
        }

        Manager manager1 = managerService.getOneManager(manager);
        System.out.println(manager1);
        if (manager1 == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("can not find manager");
            return  wrapRecord(null);
        }

        Record record = new Record();
        record.setCounternumber(counter_number);
        record.setTrackingnumber(tracking_number);
        record.setTimeoperating(time_operating);
        record.setManager(manager);
        record.setUpdate_state(update_state);


        System.out.println("recordpostinsert");
        Record record1 = recordService.insertOneRecord(record);
        System.out.println(record1);
        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("message", "Access denied.");
        return wrapRecord(record1);
    }

    @PutMapping
    public JSONObject updateOneRecord(@RequestBody JSONObject jsonObject
    ) {
        Integer id = jsonObject.getInteger("record_id");
        String counter_number = jsonObject.getString("counter_number");
        String tracking_number = jsonObject.getString("tracking_number");
        String time_operating = jsonObject.getString("time_operating");
        String manager = jsonObject.getString("manager");
        String update_state = jsonObject.getString("update_state");
        //去focus表中找id，有的话继续，没有的话返回
        Record r = recordService.getOneRecord(id);
        if (r == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapRecord(r);
        }

        Product product = productService.getOneProduct(tracking_number);
        System.out.println(product);
        if (product == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapProduct(product);
        }

        Manager manager1 = managerService.getOneManager(manager);
        System.out.println(manager1);
        if (manager1 == null ) {
            JSONObject res = new JSONObject();
            res.put("code", -1);
            res.put("message", "Access denied.");
            System.out.println("getonenull");
            return wrapManager(manager1);
        }
        Record record = new Record();

        record.setRecordId(id);
        record.setCounternumber(counter_number);
        record.setTrackingnumber(tracking_number);
        record.setTimeoperating(time_operating);
        record.setManager(manager);
        record.setUpdate_state(update_state);

        System.out.println("recordpostupdate");
        Record record1 =recordService.updateOneRecord(record);
        System.out.println(record1);
        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("message", "Access denied.");
        return wrapRecord(record1);
    }

    @DeleteMapping
    public Object deleteOneRecord( @RequestParam(value = "id",required = false) int id
    ) {
        System.out.println("recorddeleteone");

        if (!recordService.deleteOneRecord(id)) {
            System.out.println("not found");
            return new Message(-1, "User not found.");
        } else {
            System.out.println("OK");
            return new Message(0, "OK");
        }
    }
}
