package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BurgerDaoImpl implements BurgerDao{
    private EntityManager entityManager;

    @Autowired
    public BurgerDaoImpl(EntityManager entityManager){
        this.entityManager= entityManager;
    }
    @Transactional
    @Override
    public Burger save(Burger burgerEntity) {
        BurgerValidation.checkBurger(burgerEntity);
        this.entityManager.persist(burgerEntity);
        return null;
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query= entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        return query.getResultList();
    }

    @Override
    public Burger findById(long id) {
        BurgerValidation.checkLessThanZero("id", id);
        Burger burger= entityManager.find(Burger.class, id);
        BurgerValidation.checkBurgerNull(burger, id);
        return burger;
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        BurgerValidation.checkBurger(burger);
        return entityManager.merge(burger);
    }

    @Override
    public List<Burger> findByPrice(int price) {
        BurgerValidation.checkLessThanZero("price", price);
        TypedQuery<Burger> query= entityManager.createQuery("SELECT b FROM Burger b WHERE b.price> :price ORDER BY b.price DESC", Burger.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> query= entityManager.createQuery("SELECT b form Burger b WHERE b.breadType =:breadType ORDER BY b.name DESC", Burger.class);
        query.setParameter("breadType", breadType);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        BurgerValidation.checkNull("content", content);
        TypedQuery<Burger> query= entityManager.createQuery("SELECT b form Burger b WHERE b.contents LIKE CONCAT('%',:content,'%') ORDER BY b.name DESC", Burger.class);
        query.setParameter("content", content);
        return query.getResultList();
    }

    @Override
    public Burger remove(long id) {
        Burger foundBurger= findById(id);
        this.entityManager.remove(foundBurger);
        return foundBurger;
    }
}
