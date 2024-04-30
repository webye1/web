package com.reins.bookstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "wxuser")
public class Wxuser {
    public static final Integer ADMIN_TYPE_ID = 0;
    private String open_id;
    private String name;
    private String avatar;

    @Id
    @Basic
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "open_id")
    public String getOpenid() {
        return open_id;
    }

    public void setOpenid(String open_id) {
        this.open_id = open_id;
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
                "open_id=" + open_id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }


}
