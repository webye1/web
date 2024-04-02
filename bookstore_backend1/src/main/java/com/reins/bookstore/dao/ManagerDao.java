package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Manager;

import java.util.List;

public interface ManagerDao {
    List<Manager> getAllFocus();

    Manager getOneManager(Integer id);

    Manager insertOneManager(Manager manager);

    Manager updateOneManager(Manager manager);

    void deleteOneManager(int id);

    Manager getOneManager(String manager);

    Manager findOne(String name, String password);
}
