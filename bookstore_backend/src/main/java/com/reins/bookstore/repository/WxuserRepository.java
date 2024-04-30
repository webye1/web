package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Focus;
import com.reins.bookstore.entity.Wxuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface WxuserRepository extends JpaRepository<Wxuser, Integer> {
    @Query(value = "select * from wxuser",nativeQuery = true)
    List<Wxuser> getAllWxuser();

    @Query(value = "select * from wxuser where open_id =?1 ",nativeQuery = true)
    Wxuser getWXuser(String id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO wxuser (open_id, name, avatar) VALUES (?1, ?2,?3) ",nativeQuery = true)
    Integer insertone(String open_id,String name, String avatar);
}
