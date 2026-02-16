package com.poop_tracker.service.impl;

import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.entity.User;
import com.poop_tracker.exception.ResourceNotFoundException;
import com.poop_tracker.mapper.UserMapper;
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

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + userId));

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.mapToUser(userDTO);
        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }
}
