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

    @GetMapping("/{foodname}")
    public ResponseEntity<Food> getFoodByName(@PathVariable String foodname) {
        return ResponseEntity.ok(foodService.getFoodByName(foodname));
    }

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.createFood(food));
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<String> deleteFood(@PathVariable Long foodId) {
        return ResponseEntity.ok(foodService.deleteFoodById(foodId));
    }
}
