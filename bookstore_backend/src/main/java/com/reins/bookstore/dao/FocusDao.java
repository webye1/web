package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Focus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface FocusDao {

    List<Focus> getAllFocus();

    Focus insertOneFocus(Focus focus);

    Focus updateOneFocus(Focus focus);

    Focus getOneFocus(Integer id);

    void deleteOneFocus(Integer id);

    Focus getOneFocus(Integer userid, String trackingnum);
}
