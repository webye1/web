package com.reins.bookstore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "focus")
public class Focus {
    public static final Integer ADMIN_TYPE_ID = 0;
    private Integer id;
    private Integer user_id;
    private String tracking_number;

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid")
    public Integer getUserid() {
        return user_id;
    }

    public void setUserid(Integer user_id) {
        this.user_id = user_id;
    }

    @Basic
    @Column(name = "tracking_num")
    public String getTrackingnum() {
        return tracking_number;
    }

    public void setTrackingnum(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userid='" + user_id + '\'' +
                ", tracking_number='" + tracking_number + '\'' +
                '}';
    }
}
