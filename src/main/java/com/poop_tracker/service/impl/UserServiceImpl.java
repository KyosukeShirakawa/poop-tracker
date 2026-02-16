package com.poop_tracker.service.impl;

import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.mapper.UserMapper;
import com.poop_tracker.repository.UserRepository;
import com.poop_tracker.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }
}
