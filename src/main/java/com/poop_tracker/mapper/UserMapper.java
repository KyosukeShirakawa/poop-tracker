package com.poop_tracker.mapper;

import com.poop_tracker.dto.CreateUserDTO;
import com.poop_tracker.dto.DailyLogDto;
import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.entity.DailyLog;
import com.poop_tracker.entity.User;

import java.util.List;

public class UserMapper {
    public static UserDTO mapToUserDto(User user) {
        UserDTO userDto =  new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        List<DailyLog> logs = user.getLogs();
        if(logs != null) {
            userDto.setLogs(logs.stream().map((log)-> DailyLogMapper.mapToDailyLogDto(log)).toList());
        }

        userDto.setSafeFoodList(user.getSafeFoodList());
        userDto.setAvoidFoodList(user.getAvoidFoodList());

        return userDto;
    }

    public static User mapToUser(UserDTO userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        List<DailyLog> logs = userDto.getLogs().stream().map((log) -> DailyLogMapper.mapToDailyLog(log)).toList();
        user.setLogs(logs);
        user.setSafeFoodList(userDto.getSafeFoodList());
        user.setAvoidFoodList(userDto.getAvoidFoodList());

        return user;
    }

    public static User mapToUserForCreate(CreateUserDTO userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
