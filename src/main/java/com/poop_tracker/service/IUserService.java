package com.poop_tracker.service;

import com.poop_tracker.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO createUser(UserDTO userDTO);
}
