package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Banner;

public interface BannerDao {
    Banner getOne(int count);

    int getAll();

    Banner insertOne(int id,Banner banner);

    Banner updateOne(Banner banner);

    void deleteOne(int i);

    void deleteall();
}
