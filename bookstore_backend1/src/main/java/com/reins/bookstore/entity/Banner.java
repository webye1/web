package com.reins.bookstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "banner")
public class Banner {
    public static final Integer ADMIN_TYPE_ID = 0;
    private Integer banner_id;
    private String url;

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    public Integer getId() {
        return banner_id;
    }

    public void setId(Integer banner_id) {
        this.banner_id = banner_id;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "banner_id=" + banner_id +
                ", url='" + url + '\'' +
                '}';
    }
}
