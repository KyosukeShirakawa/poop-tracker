package com.poop_tracker.mapper;

import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.entity.User;

public class UserMapper {
    public static UserDTO mapToUserDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getLogs(),
                user.getSafeFoodList(),
                user.getAvoidFoodList()
        );
    }

    public static User mapToUser(UserDTO userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getLogs(),
                userDto.getSafeFoodList(),
                userDto.getAvoidFoodList()
        );
    }
}
