package com.reins.bookstore.service;

import com.reins.bookstore.entity.Wxuser;

import java.util.List;

public interface WxuserService {
    List<Wxuser> getAllWxuser();

    Wxuser getOneWxuser(String id);

    Wxuser insertOneWxuser(Wxuser wxuser1);

    Wxuser updateOneWxuser(Wxuser wxuser);

    boolean deleteOneWxuser(int id);
}
