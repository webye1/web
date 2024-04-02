package com.reins.bookstore.daoimpl;


import com.reins.bookstore.dao.ManagerDao;
import com.reins.bookstore.entity.Focus;
import com.reins.bookstore.entity.Manager;
import com.reins.bookstore.entity.Wxuser;
import com.reins.bookstore.repository.FocusRepository;
import com.reins.bookstore.repository.ManagerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManagerDaoImpl implements ManagerDao {
    private final ManagerRepository managerRepository;

    public ManagerDaoImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Manager> getAllFocus() {
        List<Manager> managers = managerRepository.getAllManager();
        return managers;
    }

    @Override
    public Manager getOneManager(Integer id) {
        System.out.println("managerDaogetone");
        Manager manager = managerRepository.getOne(id);//insertOneFocus(focus.getUserid(),focus.getTrackingnum());
        return manager;
    }

    @Override
    public Manager insertOneManager(Manager manager) {
        System.out.println("managerDaoinsert");
        try {
            return managerRepository.save(manager);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Manager updateOneManager(Manager manager) {
        System.out.println("managerDaoupdate");
        Manager manager1 = managerRepository.save(manager);//insertOneFocus(focus.getUserid(),focus.getTrackingnum());
        return manager1;
    }

    @Override
    public void deleteOneManager(int id) throws IllegalArgumentException{
        managerRepository.deleteById(id);
    }

    @Override
    public Manager getOneManager(String manager) {
        Manager manager1 = managerRepository.getOneManager(manager);
        return manager1;
    }

    @Override
    public Manager findOne(String name, String password) {
        System.out.println("name:"+name+" password:"+password);
        Manager manager1 = managerRepository.findOne(name,password);
        return manager1;
    }


}
