package com.reins.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.reins.bookstore.entity.Manager;
import com.reins.bookstore.entity.Product;
import com.reins.bookstore.entity.Record;

import com.reins.bookstore.service.ManagerService;
import com.reins.bookstore.service.ProductService;
import com.reins.bookstore.service.RecordService;
import com.reins.bookstore.utils.Message;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.reins.bookstore.utils.Adapter.*;
import static java.lang.Math.min;

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

    @Scheduled(cron = "0 15 10 ? * MON")
    private void deleteRecord(){
        List<Record> records = recordService.getAllRecord();
        for(Record it :records){

            String time = it.getTimeoperating();
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date = null;
            try {
                date = sdf.parse(time);
                Date currentDate = new Date();
                //System.out.println("String类型的时间转换为Date类型成功：" + date);
                //System.out.println("当前时间：" + currentDate);
                long days = ChronoUnit.DAYS.between(date.toInstant(), currentDate.toInstant());
                System.out.println("差：" + days);
                if (days >= 180) {
                    deleteOneRecord(it.getRecordId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @RequestMapping("/{id}")
    public JSONObject getOneRecord(@PathVariable Integer id) {
        System.out.println("getonerecord");
        System.out.println(id);
        Record record = recordService.getOneRecord(id);
        return wrapRecord(record);
    }

    @RequestMapping("/insert")
    public String insertAllRecord(@RequestBody List<JSONObject> jsonObjectList
    ) {
        List<Record> recordlist = new ArrayList<>();
        int num= jsonObjectList.size();
        int i = 0;
        String s = "";
        while(num-1000*i>0){
            for(int j=i*1000;j<min(i*1000+1000,num);j++){
                JSONObject jsonObject = jsonObjectList.get(j);
                if (jsonObject.getString("tracking_number")!=null && jsonObject.getString("tracking_number")!=""&&jsonObject.getString("manager")!=null && jsonObject.getString("manager")!=""&&jsonObject.getString("update_state")!=null && jsonObject.getString("update_state")!="") {
                    Record r1 = js2record(jsonObject);
                    if (r1.getTrackingnumber()!=null&&r1.getTrackingnumber()!=""&&r1.getManager()!=null&&r1.getManager()!=""&&r1.getUpdate_state()!=null&&r1.getUpdate_state()!=""){
                        recordlist.add(r1);
                    }
                }
            }
            s=  recordService.insertall(recordlist);
            i=i+1;
            recordlist.clear();
        }
        /*
        for (JSONObject jsonObject :jsonObjectList){
            if (jsonObject.getString("tracking_number")!=null && jsonObject.getString("tracking_number")!=""&&jsonObject.getString("manager")!=null && jsonObject.getString("manager")!=""&&jsonObject.getString("update_state")!=null && jsonObject.getString("update_state")!="") {
                Record r1 = js2record(jsonObject);
                if (r1.getTrackingnumber()!=null&&r1.getTrackingnumber()!=""&&r1.getManager()!=null&&r1.getManager()!=""&&r1.getUpdate_state()!=null&&r1.getUpdate_state()!=""){
                    recordlist.add(r1);
                }
            }
        }
        System.out.println(recordlist);
        String s = recordService.insertall(recordlist);
        */
        /*
        for (JSONObject jsonObject :jsonObjectList){
            JSONObject res = new JSONObject();
            res = insertOneRecord(jsonObject);
            System.out.println("json"+res);
            System.out.println("rid"+(res.getInteger("record_id")));

            if (res.getInteger("record_id") == -1){
                results.add("Record Error!");
                System.out.println("-1");
            }else{
                results.add("Record OK!");
                System.out.println("ok");
            }

        }
        */
        return s;

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
