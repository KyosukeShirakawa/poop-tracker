package com.poop_tracker.controller;

import com.poop_tracker.dto.CreateUserDTO;
import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.entity.Food;
import com.poop_tracker.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private UserServiceImpl userService;

    @GetMapping
    ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping
    ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO userDTO){
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/{userId}")
    ResponseEntity<UserDTO> updateUserById(@PathVariable Long userId, @RequestBody CreateUserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUserById(userId, userDTO));
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @GetMapping("/{userId}/safe-foods")
    ResponseEntity<List<Food>> getSafeFoods(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getSafeFoods(userId));
    }


    @PostMapping("/{userId}/safe-foods")
    ResponseEntity<UserDTO> addFoodToSafeList(@PathVariable Long userId, @RequestBody String foodname) {
        return ResponseEntity.ok(userService.addFoodToSafeList(userId, foodname));
    }

    @DeleteMapping("/{userId}/safe-foods/{foodId}")
    ResponseEntity<String> getSafeFoods(@PathVariable Long userId, @PathVariable Long foodId) {
        return ResponseEntity.ok(userService.removeFoodFromSafeList(userId, foodId));
    }

    @PostMapping("/{userId}/avoid-foods")
    ResponseEntity<UserDTO> addFoodToAvoidList(@PathVariable Long userId, @RequestBody String foodname) {
        return ResponseEntity.ok(userService.addFoodToAvoidList(userId, foodname));
    }

    @DeleteMapping("/{userId}/avoid-foods/{foodId}")
    ResponseEntity<String> getAvoidFoods(@PathVariable Long userId, @PathVariable Long foodId) {
        return ResponseEntity.ok(userService.removeFoodFromAvoidList(userId, foodId));
    }



}
