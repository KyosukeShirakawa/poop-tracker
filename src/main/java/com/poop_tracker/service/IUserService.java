package com.poop_tracker.service;

import com.poop_tracker.dto.CreateUserDTO;
import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.entity.Food;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long userId);
    UserDTO createUser(CreateUserDTO userDTO);
    UserDTO updateUserById(Long userID, CreateUserDTO userDTO);
    String deleteUser(Long userId);
    List<Food> getSafeFoods(Long userId);
    Food addFoodToSafeList(Long userId, Food food);
    List<Food> removeFoodFromSafeList(Long userId, Long foodId);
    Food addFoodToAvoidList(Long userId, Food food);
    String removeFoodFromAvoidList(Long userId, Long foodId);


}
