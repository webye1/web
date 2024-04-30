package com.reins.bookstore.service;

import com.reins.bookstore.entity.Banner;

public interface BannerService {
    Banner getOne(int count);

    int getAll();

    Banner insertOne(int id,Banner banner);

    Banner updateOne(Banner b);

    boolean deleteOne(int i);


    boolean deleteall();
}
