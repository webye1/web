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
            json.put("focus_id", -1);
            json.put("open_id", null);
            json.put("tracking_number", null);
            json.put("addition", null);
            return json;
        }
        json.put("foucus_id", focus.getFocusid());
        json.put("open_id", focus.getOpenid());
        json.put("tracking_number", focus.getTrackingnum());
        json.put("addition", focus.getAddition());
        return json;
    }

    public static JSONObject wrapWxuser(Wxuser wxuser) {
        JSONObject json = new JSONObject();
        if (wxuser == null) {
            json.put("open_id", null);
            json.put("name", null);
            json.put("avatar", null);
            return json;
        }else {
            json.put("open_id", wxuser.getOpenid());
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
            json.put("warehouse_time",null);
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
            json.put("warehouse_time",product.getWarehousetime());
            json.put("finish_time",product.getFinishtime());
            return json;
        }
    }

    public static Product js2product(JSONObject jsonObject) {
        Product product = new Product();
        String product_name = jsonObject.getString("product_name");
        String proxy = jsonObject.getString("proxy");
        String counter_number = jsonObject.getString("counter_number");
        String tracking_number = jsonObject.getString("tracking_number");
        String customer = jsonObject.getString("customer");
        Integer count = jsonObject.getInteger("count");
        Float weight = jsonObject.getFloat("weight");
        String state = jsonObject.getString("state");
        String op_time = jsonObject.getString("op_time");
        product.setProductname(product_name);
        product.setProxy(proxy);
        product.setCounternumber(counter_number);
        product.setTrackingnumber(tracking_number);
        product.setCustomer(customer);
        product.setCount(count);
        product.setWeight(weight);

        product.setOptime(op_time);
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
        return product;
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
    public static Record js2record(JSONObject jsonObject) {
        Record r = new Record();
        String counter_number = jsonObject.getString("counter_number");
        String tracking_number = jsonObject.getString("tracking_number");
        String time_operating = jsonObject.getString("time_operating");
        String manager = jsonObject.getString("manager");
        String update_state = jsonObject.getString("update_state");
        r.setCounternumber(counter_number);
        r.setTrackingnumber(tracking_number);
        r.setTimeoperating(time_operating);
        r.setManager(manager);
        r.setUpdate_state(update_state);
        return r;
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

