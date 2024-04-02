package com.reins.bookstore.service;

import com.reins.bookstore.entity.Focus;
import org.springframework.data.domain.Page;

import java.util.List;
public interface FocusService {
    List<Focus> getAllFocus();
    Focus insertOneFocus(Focus focus);

    Focus updateOneFocus(Focus focus);

    Focus getOneFocus(Integer id);

    boolean deleteOneFocus(Integer id);

    Focus getOneFocus(Integer userid, String trackingnum);
}
