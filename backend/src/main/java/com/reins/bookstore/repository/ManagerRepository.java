package com.reins.bookstore.repository;


import com.reins.bookstore.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    @Query(value = "select * from manager",nativeQuery = true)
    List<Manager> getAllManager();

    @Query(value = "select * from manager where name = ?1",nativeQuery = true)
    Manager getOneManager(String manager);


    @Query(value = "select * from manager where name = ?1 and password = ?2",nativeQuery = true)
    Manager findOne(String name, String password);
}
