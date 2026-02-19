package com.poop_tracker.controller;

import com.poop_tracker.dto.CreateUserDTO;
import com.poop_tracker.dto.UserDTO;
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
        userService.deleteUser(userId);
        return ResponseEntity.ok("User successfully delete with ID: " + userId);
    }


}
