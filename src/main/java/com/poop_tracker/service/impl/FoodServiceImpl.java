package com.poop_tracker.service.impl;

import com.poop_tracker.entity.Food;
import com.poop_tracker.exception.ResourceNotFoundException;
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
    public Food getFoodByName(String foodname) {
        return foodRepository.findByName(foodname)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with name: " + foodname));
    }

    @Override
    public Food createFood(Food food) {
        String foodName = food.getName().trim().toLowerCase();
        food.setName(foodName);

       return  foodRepository.findByName(foodName).orElseGet(() -> foodRepository.save(food));
    }

    @Override
    public String deleteFoodById(Long foodId) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with Id: " + foodId));

        foodRepository.delete(food);

        return "Food is successfully deleted";
    }
}
