package com.poop_tracker.service;

import com.poop_tracker.dto.CreateUserDTO;
import com.poop_tracker.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long userId);
    UserDTO createUser(CreateUserDTO userDTO);
    UserDTO updateUserById(Long userID, CreateUserDTO userDTO);
    void deleteUser(Long userId);

}
