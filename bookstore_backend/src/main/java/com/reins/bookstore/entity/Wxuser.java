package com.reins.bookstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "wxuser")
public class Wxuser {
    public static final Integer ADMIN_TYPE_ID = 0;
    private Integer userid;
    private String name;
    private String avatar;

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userid) {
        this.userid = userid;
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
    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Wxuser{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }


}
