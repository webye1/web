package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Focus;

import java.util.List;

public interface FocusDao {

    List<Focus> getAllFocus();

    Focus insertOneFocus(Focus focus);

    Focus updateOneFocus(Focus focus);

    Focus getOneFocus(Integer id);

    void deleteOneFocus(Integer id);

    Focus getOneFocus(String userid, String trackingnum);

    List<Focus> getUnionFocus(String unionId);
}
