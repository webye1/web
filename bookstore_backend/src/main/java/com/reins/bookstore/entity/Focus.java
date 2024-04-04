package com.reins.bookstore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "focus")
public class Focus {
    public static final Integer ADMIN_TYPE_ID = 0;
    private Integer focus_id;
    private Integer union_id;
    private String tracking_number;
    private String addition;

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "focus_id")
    public Integer getFocusid() {
        return focus_id;
    }

    public void setFocusid(Integer focus_id) {
        this.focus_id = focus_id;
    }

    @Basic
    @Column(name = "union_id")
    public Integer getUnionid() {
        return union_id;
    }

    public void setUnionid(Integer union_id) {
        this.union_id = union_id;
    }

    @Basic
    @Column(name = "tracking_num")
    public String getTrackingnum() {
        return tracking_number;
    }

    public void setTrackingnum(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    @Basic
    @Column(name = "addition")
    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    @Override
    public String toString() {
        return "User{" +
                "focus_id=" + focus_id +
                ", union_id='" + union_id + '\'' +
                ", tracking_number='" + tracking_number + '\'' +
                ", addition='" + addition + '\'' +
                '}';
    }
}
