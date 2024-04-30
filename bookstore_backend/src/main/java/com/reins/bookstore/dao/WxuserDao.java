package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Wxuser;

import java.util.List;

public interface WxuserDao {
    List<Wxuser> getAllWxuser();

    Wxuser getOneWxuser(String id);

    Wxuser insertOneWxuser(Wxuser wxuser);

    Wxuser updateOneWxuser(Wxuser wxuser);

    void deleteOneWxuser(int id);
}
