package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Integer> {
    @Query(value = "select * from record",nativeQuery = true)
    List<Record> getAllRecord();
}
