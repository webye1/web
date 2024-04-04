package com.reins.bookstore.service;

import com.reins.bookstore.entity.Manager;

import java.util.List;

public interface ManagerService {
    List<Manager> getAllManager();

    Manager getOneManager(Integer id);

    Manager insertOneManager(Manager manager);

    Manager updateOneManager(Manager manager);

    boolean deleteOneManager(int id);

    Manager getOneManager(String manager);

    Manager findOne(String name, String password);
}
