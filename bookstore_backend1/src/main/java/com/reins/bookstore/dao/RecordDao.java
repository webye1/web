package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Record;

import java.util.List;

public interface RecordDao {
    List<Record> getAllRecord();

    Record getOneRecord(Integer id);

    Record insertOneRecord(Record record);

    Record updateOneRecord(Record record);

    void deleteOneProduct(int id);
}
