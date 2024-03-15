package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.ManagerDao;
import com.reins.bookstore.dao.RecordDao;
import com.reins.bookstore.entity.Manager;
import com.reins.bookstore.entity.Product;
import com.reins.bookstore.entity.Record;
import com.reins.bookstore.service.RecordService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"lastResult"})
public class RecordServiceImpl implements RecordService {
    private final RecordDao recordDao;

    public RecordServiceImpl(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public List<Record> getAllRecord() {
        List<Record> Records = recordDao.getAllRecord();
        return Records;
    }

    @Override
    public Record getOneRecord(Integer id) {
        System.out.println("recordservicegetone");
        Record record = recordDao.getOneRecord(id);
        return record;
    }

    @Override
    public Record insertOneRecord(Record record) {
        System.out.println("recordserviceinsert");
        Record record1 = recordDao.insertOneRecord(record);
        return record1;
    }

    @Override
    public Record updateOneRecord(Record record) {
        System.out.println("recordserviceupdate");
        Record record1 = recordDao.updateOneRecord(record);
        return record1;
    }

    @Override
    public boolean deleteOneRecord(int id) {
        System.out.println("recordservicedeleteone");
        try {
            recordDao.deleteOneProduct(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
