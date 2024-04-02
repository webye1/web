package com.reins.bookstore.serviceimpl;


import com.reins.bookstore.dao.FocusDao;
import com.reins.bookstore.dao.ManagerDao;
import com.reins.bookstore.entity.Focus;
import com.reins.bookstore.entity.Manager;
import com.reins.bookstore.entity.Wxuser;
import com.reins.bookstore.service.ManagerService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"lastResult"})
public class ManagerServiceImpl implements ManagerService {
    private final ManagerDao managerDao;

    public ManagerServiceImpl(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Override
    public List<Manager> getAllManager() {
        List<Manager> managers = managerDao.getAllFocus();
        return managers;
    }

    @Override
    public Manager getOneManager(Integer id) {
        System.out.println("managerservicegetone");
        Manager manager = managerDao.getOneManager(id);
        return manager;
    }

    @Override
    public Manager insertOneManager(Manager manager) {
        System.out.println("managerserviceinsert");
        Manager manager1 = managerDao.insertOneManager(manager);
        return manager1;
    }

    @Override
    public Manager updateOneManager(Manager manager) {
        System.out.println("managerserviceupdate");
        Manager manager1 = managerDao.updateOneManager(manager);
        return manager1;
    }

    @Override
    public boolean deleteOneManager(int id) {
        System.out.println("managerservicedeleteone");
        try {
            managerDao.deleteOneManager(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Manager getOneManager(String manager) {
        System.out.println("managerservicegetoneby");
        Manager manager1 = managerDao.getOneManager(manager);
        return manager1;
    }

    @Override
    public Manager findOne(String name, String password) {
        System.out.println("managerservicefindoneby");
        System.out.println("name:"+name+" password:"+password);
        Manager manager1 = managerDao.findOne(name,password);
        return manager1;
    }


}
