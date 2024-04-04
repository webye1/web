package com.reins.bookstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "wxuser")
public class Wxuser {
    public static final Integer ADMIN_TYPE_ID = 0;
    private Integer union_id;
    private String name;
    private String avatar;

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "union_id")
    public Integer getUnionid() {
        return union_id;
    }

    public void setUnionid(Integer union_id) {
        this.union_id = union_id;
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
                "union_id=" + union_id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }


}
