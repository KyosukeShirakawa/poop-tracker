package com.poop_tracker.service;

import com.poop_tracker.entity.Food;

import java.util.List;


public interface IFoodService {
    List<Food> getAllFoods();
    Food getFoodByName(String foodname);
    Food createFood(Food food);
    String deleteFoodById(Long id);
}
