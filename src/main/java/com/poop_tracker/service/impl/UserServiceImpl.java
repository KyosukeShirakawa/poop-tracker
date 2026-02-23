package com.poop_tracker.service.impl;

import com.poop_tracker.dto.CreateUserDTO;
import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.entity.Food;
import com.poop_tracker.entity.User;
import com.poop_tracker.exception.ResourceNotFoundException;
import com.poop_tracker.mapper.UserMapper;
import com.poop_tracker.repository.FoodRepository;
import com.poop_tracker.repository.UserRepository;
import com.poop_tracker.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.cfg.MapperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;
    private FoodRepository foodRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> UserMapper.mapToUserDto(user)).toList();
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + userId));

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDTO createUser(CreateUserDTO userDTO) {
        User user = UserMapper.mapToUserForCreate(userDTO);
        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDTO updateUserById(Long userID, CreateUserDTO userDTO) {
        User user = userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userID));

        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());

        User updatedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public String deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        userRepository.delete(user);

        userRepository.deleteById(userId);

        return "User is successfully deleted";
    }

    @Override
    public List<Food> getSafeFoods(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with Id: " + userId));

        return new ArrayList<>(user.getSafeFoodList());
    }

    @Override
    public UserDTO addFoodToSafeList(Long userId, String foodname) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with Id: " + userId));
        Food food = foodRepository.findByName(foodname).orElseGet(() -> foodRepository.save(new Food(foodname)));

        user.getSafeFoodList().add(food);

        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public String removeFoodFromSafeList(Long userId, Long foodId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with Id: " + userId));
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new ResourceNotFoundException("Food not found with Id: " + foodId));

        user.getSafeFoodList().remove(food);

        userRepository.save(user);
        return "Food is successfully removed from the safe list";
    }
}
