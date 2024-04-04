package com.reins.bookstore.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "manager")
public class Manager {

    public static final Integer ADMIN_TYPE_ID = 0;
    private Integer manager_id;
    private String name;
    private String password;
    private Integer type;

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    public Integer getManagerId() {
        return manager_id;
    }

    public void setManagerId(Integer manager_id) {
        this.manager_id = manager_id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "User{" +
                "manager_id=" + manager_id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
