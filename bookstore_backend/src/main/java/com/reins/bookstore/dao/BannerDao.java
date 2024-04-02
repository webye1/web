package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Banner;

public interface BannerDao {
    Banner getOne(int count);

    int getAll();

    Banner insertOne(Banner banner);

    Banner updateOne(Banner banner);

    void deleteOne(int i);
}
