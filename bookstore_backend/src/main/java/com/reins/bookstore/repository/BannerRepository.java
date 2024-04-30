package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Banner;
import com.reins.bookstore.entity.Focus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;



public interface BannerRepository extends JpaRepository<Banner, Integer> {
    @Query(value = "SELECT * FROM banner where banner_id = ?1 ",nativeQuery = true)
    Banner getOnebanner(int count);

    @Query(value = "SELECT COUNT(*) FROM banner",nativeQuery = true)
    int getAll();
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO banner (banner_id, url) VALUES (?1, ?2)",nativeQuery = true)
    Integer insertOne(int id,String url);


    @Transactional
    @Modifying
    @Query(value = " DELETE FROM banner WHERE banner_id > 1;",nativeQuery = true)
    Integer deleteall();

}
