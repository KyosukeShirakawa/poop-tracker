package com.poop_tracker.mapper;

import com.poop_tracker.dto.CreateUserDTO;
import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.entity.User;

public class UserMapper {
    public static UserDTO mapToUserDto(User user) {
        UserDTO userDto =  new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLogs(user.getLogs());
        userDto.setSafeFoodList(user.getSafeFoodList());
        userDto.setAvoidFoodList(user.getAvoidFoodList());

        return userDto;
    }

    public static User mapToUser(UserDTO userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLogs(userDto.getLogs());
        user.setSafeFoodList(userDto.getSafeFoodList());
        user.setAvoidFoodList(userDto.getAvoidFoodList());

        return user;
    }

    public static User mapToUserForCreate(CreateUserDTO userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setLogs(userDto.getLogs());
        user.setSafeFoodList(userDto.getSafeFoodList());
        user.setAvoidFoodList(userDto.getAvoidFoodList());

        return user;
    }
}
