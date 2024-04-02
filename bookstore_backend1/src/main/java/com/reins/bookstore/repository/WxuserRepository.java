package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Focus;
import com.reins.bookstore.entity.Wxuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WxuserRepository extends JpaRepository<Wxuser, Integer> {
    @Query(value = "select * from wxuser",nativeQuery = true)
    List<Wxuser> getAllWxuser();
}
