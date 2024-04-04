package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.RecordDao;
import com.reins.bookstore.entity.Manager;
import com.reins.bookstore.entity.Product;
import com.reins.bookstore.entity.Record;
import com.reins.bookstore.repository.ManagerRepository;
import com.reins.bookstore.repository.RecordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordDaoImpl implements RecordDao {
    private final RecordRepository recordRepository;

    public RecordDaoImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> getAllRecord() {
        List<Record> Records = recordRepository.getAllRecord();
        return Records;
    }

    @Override
    public Record getOneRecord(Integer id) {
        System.out.println("recordDaogetone");
        Record record = recordRepository.getOne(id);//insertOneFocus(focus.getUserid(),focus.getTrackingnum());
        return record;
    }

    @Override
    public Record insertOneRecord(Record record) {
        System.out.println("recordDaoinsert");
        try {
            return recordRepository.save(record);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Record updateOneRecord(Record record) {
        System.out.println("recordDaoupdate");
        Record record1 = recordRepository.save(record);//insertOneFocus(focus.getUserid(),focus.getTrackingnum());
        return record1;
    }

    @Override
    public void deleteOneProduct(int id) throws IllegalArgumentException{
        recordRepository.deleteById(id);
    }

    @Override
    public String insertall(List<Record> recordlist) {
        recordRepository.saveAll(recordlist);
        String s = "Insert all record!";
        return s;
    }
}
