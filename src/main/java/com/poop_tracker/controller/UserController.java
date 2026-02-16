package com.poop_tracker.controller;

import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.entity.User;
import com.poop_tracker.service.IUserService;
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

    @PostMapping
    ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.createUser(userDTO));
    }
}
