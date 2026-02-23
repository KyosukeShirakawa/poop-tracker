package com.poop_tracker.controller;

import com.poop_tracker.entity.Food;
import com.poop_tracker.service.impl.FoodServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("foods")
@AllArgsConstructor
public class FoodController {
    private FoodServiceImpl foodService;

    @GetMapping
    public ResponseEntity<List<Food>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.createFood(food));
    }
}
