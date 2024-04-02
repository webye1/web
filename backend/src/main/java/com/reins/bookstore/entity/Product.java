package com.reins.bookstore.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "Product")
public class Product {

    public static final Integer ADMIN_TYPE_ID = 0;
    private Integer product_id;
    private String product_name;
    private String proxy;
    private String counter_number;
    private String tracking_number;
    private String customer;
    private Integer count;
    private Float weight;
    private String state;
    private String op_time;
    private String storeroom_time;
    private String compartment_time;
    private String port_time;
    private String station_time;
    private String clearance_time;
    private String truck_time;
    private String finish_time;


    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    public Integer getProductId() {
        return product_id;
    }

    public void setProductId(Integer product_id) {
        this.product_id = product_id;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductname() {
        return product_name;
    }

    public void setProductname(String product_name) {
        this.product_name = product_name;
    }

    @Basic
    @Column(name = "proxy")
    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    @Basic
    @Column(name = "counter_number")
    public String getCounternumber() {
        return counter_number;
    }

    public void setCounternumber(String counter_number) {
        this.counter_number = counter_number;
    }

    @Basic
    @Column(name = "tracking_number")
    public String getTrackingnumber() {
        return tracking_number;
    }

    public void setTrackingnumber(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    @Basic
    @Column(name = "customer")
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    @Basic
    @Column(name = "weight")
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "op_time")
    public String getOptime() {
        return op_time;
    }

    public void setOptime(String op_time) {
        this.op_time = op_time;
    }

    @Basic
    @Column(name = "storeroom_time")
    public String getStoreroomtime() {
        return storeroom_time;
    }

    public void setStoreroomtime(String storeroom_time) {
        this.storeroom_time = storeroom_time;
    }

    @Basic
    @Column(name = "compartment_time")
    public String getCompartmenttime() {
        return compartment_time;
    }

    public void setCompartmenttime(String compartment_time) {
        this.compartment_time = compartment_time;
    }

    @Basic
    @Column(name = "port_time")
    public String getPorttime() {
        return port_time;
    }

    public void setPorttime(String port_time) {
        this.port_time = port_time;
    }

    @Basic
    @Column(name = "station_time")
    public String getStationtime() {
        return station_time;
    }

    public void setStationtime(String station_time) {
        this.station_time = station_time;
    }

    @Basic
    @Column(name = "clearance_time")
    public String getClearancetime() {
        return clearance_time;
    }

    public void setClearancetime(String clearance_time) {
        this.clearance_time = clearance_time;
    }

    @Basic
    @Column(name = "truck_time")
    public String getTrucktime() {
        return truck_time;
    }

    public void setTrucktime(String truck_time) {
        this.truck_time = truck_time;
    }

    @Basic
    @Column(name = "finish_time")
    public String getFinishtime() {
        return finish_time;
    }

    public void setFinishtime(String finish_time) {
        this.finish_time = finish_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "product_id=" + product_id +
                "product_name=" + product_name + '\'' +
                "proxy=" + proxy + '\'' +
                ", counter_number='" + counter_number + '\'' +
                ", tracking_number='" + tracking_number + '\'' +
                ", customer='" + customer + '\'' +
                ", count='" + count + '\'' +
                ", weight='" + weight + '\'' +
                ", state='" + state + '\'' +
                ", op_time='" + op_time + '\'' +
                ", storeroom_time='" + storeroom_time + '\'' +
                ", compartment_time='" + compartment_time + '\'' +
                ", port_time='" + port_time + '\'' +
                ", station_time='" + station_time + '\'' +
                ", clearance_time='" + clearance_time + '\'' +
                ", truck_time='" + truck_time + '\'' +
                ", finish_time='" + finish_time + '\'' +
                '}';
    }
}
