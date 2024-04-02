package com.reins.bookstore.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "record")
public class Record {
    public static final Integer ADMIN_TYPE_ID = 0;
    private Integer record_id;
    private String counter_number;
    private String tracking_number;
    private String time_operating;
    private String manager;
    private String update_state;



    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    public Integer getRecordId() {
        return record_id;
    }

    public void setRecordId(Integer record_id) {
        this.record_id = record_id;
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
    @Column(name = "time_operating")
    public String getTimeoperating() {
        return time_operating;
    }

    public void setTimeoperating(String time_operating) {
        this.time_operating = time_operating;
    }

    @Basic
    @Column(name = "manager")
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Basic
    @Column(name = "update_state")
    public String getUpdate_state() {
        return update_state;
    }

    public void setUpdate_state(String update_state) {
        this.update_state = update_state;
    }



    @Override
    public String toString() {
        return "User{" +
                "record_id=" + record_id +
                ", counter_number='" + counter_number + '\'' +
                ", tracking_number='" + tracking_number + '\'' +
                ", time_operating='" + time_operating + '\'' +
                ", manager='" + manager + '\'' +
                ", update_state='" + update_state + '\'' +

                '}';
    }
}
