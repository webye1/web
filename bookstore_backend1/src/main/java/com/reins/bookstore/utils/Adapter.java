package com.reins.bookstore.utils;

import com.reins.bookstore.entity.*;
import com.alibaba.fastjson.JSONObject;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Adapter {
    public static JSONObject wrapFocus(Focus focus) {
        JSONObject json = new JSONObject();
        if (focus == null) {
            json.put("id", -1);
            json.put("userid", -1);
            json.put("tracking_number", null);
            return json;
        }
        json.put("id", focus.getId());
        json.put("userid", focus.getUserid());
        json.put("tracking_number", focus.getTrackingnum());
        return json;
    }

    public static JSONObject wrapWxuser(Wxuser wxuser) {
        JSONObject json = new JSONObject();
        if (wxuser == null) {
            json.put("id", -1);
            json.put("name", null);
            json.put("avatar", null);
            return json;
        }else {
            json.put("id", wxuser.getUserId());
            json.put("name", wxuser.getName());
            json.put("avatar", wxuser.getAvatar());
            return json;
        }
    }

    public static JSONObject wrapManager(Manager manager) {
        JSONObject json = new JSONObject();
        if (manager == null) {

            json.put("manager_id", -1);
            json.put("name", null);
            json.put("password", null);
            json.put("type",-1);
            return json;
        }else{
            json.put("manager_id", manager.getManagerId());
            json.put("name", manager.getName());
            json.put("password", manager.getPassword());
            json.put("type",manager.getType());
            return json;
        }

    }

    public static JSONObject wrapProduct(Product product) {
        JSONObject json = new JSONObject();
        if (product == null){
            json.put("product_id", -1);
            json.put("product_name", null);
            json.put("proxy", null);
            json.put("counter_number", null);
            json.put("tracking_number", null);
            json.put("customer",null);
            json.put("count", -1);
            json.put("weight", -1);
            json.put("state", null);
            json.put("op_time", null);
            json.put("storeroom_time",null);
            json.put("compartment_time", null);
            json.put("port_time", null);
            json.put("station_time",null);
            json.put("clearance_time", null);
            json.put("truck_time", null);
            json.put("finish_time",null);
            return json;
        }else{
            json.put("product_id", product.getProductId());
            json.put("product_name", product.getProductname());
            json.put("proxy", product.getProxy());
            json.put("counter_number", product.getCounternumber());
            json.put("tracking_number", product.getTrackingnumber());
            json.put("customer",product.getCustomer());
            json.put("count", product.getCount());
            json.put("weight", product.getWeight());
            json.put("state", product.getState());
            json.put("op_time", product.getOptime());
            json.put("storeroom_time",product.getStoreroomtime());
            json.put("compartment_time", product.getCompartmenttime());
            json.put("port_time", product.getPorttime());
            json.put("station_time",product.getStationtime());
            json.put("clearance_time", product.getClearancetime());
            json.put("truck_time", product.getTrucktime());
            json.put("finish_time",product.getFinishtime());
            return json;
        }
    }

    public static JSONObject wrapRecord(Record record) {
        JSONObject json = new JSONObject();
        if (record == null){
            json.put("record_id", -1);
            json.put("counter_number", null);
            json.put("tracking_number", null);
            json.put("time_operating",null);
            json.put("manager", null);
            json.put("update_state", null);

            return json;
        }else {
            json.put("record_id", record.getRecordId());
            json.put("counter_number", record.getCounternumber());
            json.put("tracking_number", record.getTrackingnumber());
            json.put("time_operating",record.getTimeoperating());
            json.put("manager", record.getManager());
            json.put("update_state", record.getUpdate_state());

            return json;
        }


    }

    public static JSONObject wrapBanner(Banner banner) {
        JSONObject json = new JSONObject();
        if (banner == null) {
            json.put("banner_id", -1);
            json.put("url", null);
            return json;
        }
        json.put("banner_id", banner.getId());
        json.put("url", banner.getUrl());
        return json;
    }


}

