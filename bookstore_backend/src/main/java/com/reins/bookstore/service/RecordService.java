package com.reins.bookstore.service;

import com.reins.bookstore.entity.Record;

import java.util.List;

public interface RecordService {
    List<Record> getAllRecord();

    Record getOneRecord(Integer id);

    Record insertOneRecord(Record record);

    Record updateOneRecord(Record record);

    boolean deleteOneRecord(int id);

    String insertall(List<Record> recordlist);
}
