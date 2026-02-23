package com.poop_tracker.service.impl;

import com.poop_tracker.entity.Food;
import com.poop_tracker.repository.FoodRepository;
import com.poop_tracker.service.IFoodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodServiceImpl implements IFoodService {
    FoodRepository foodRepository;

    public List<Food> getAllFoods(){
        return foodRepository.findAll();
    }

    @Override
    public Food createFood(Food food) {
        return foodRepository.save(food);
    }




}
