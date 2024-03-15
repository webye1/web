package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Focus;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FocusRepository extends JpaRepository<Focus, Integer> {
    @Query(value = "select * from focus",nativeQuery = true)
    List<Focus> getAllFocus();

    @Query(value = "select * from focus where userid = ?1 and tracking_num = ?2",nativeQuery = true)
    Focus getOnefocus(Integer userid, String trackingnum);
}
