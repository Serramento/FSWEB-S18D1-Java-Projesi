package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {
    public static void checkBurger(Burger burger){
        checkNull("name", burger.getName());
        checkNull("content", burger.getContents());
    }

    public static void checkBurgerNull(Burger burger, long id){
        if(burger == null){
            throw new BurgerException("burger with given id is not exist"+id, HttpStatus.NOT_FOUND);
        }
    }

    public static void checkNull(String fieldName, String value){
        if(value == null|| value.isEmpty()){
            throw new BurgerException(fieldName+ " cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkLessThanZero(String fieldName, int value){
        if(value <0){
            throw new BurgerException(fieldName+ " cannot be less than zero", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkLessThanZero(String fieldName, long value){
        if(value <0){
            throw new BurgerException(fieldName+ " cannot be less than zero", HttpStatus.BAD_REQUEST);
        }
    }
}
