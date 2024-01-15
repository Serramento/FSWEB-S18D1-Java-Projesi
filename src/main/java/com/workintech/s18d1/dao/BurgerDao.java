package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;

import java.util.List;

public interface BurgerDao {

    Burger save(Burger burger);

    List<Burger> findAll();

    Burger findById(long id);

    Burger update(Burger burger);
    List<Burger> findByPrice(int price);
    List<Burger> findByBreadType(BreadType breadType);
    List<Burger> findByContent(String content);

    Burger remove(long id);
}