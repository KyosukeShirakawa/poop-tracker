package com.poop_tracker.mapper;

import com.poop_tracker.dto.CreateUserDTO;
import com.poop_tracker.dto.DailyLogDto;
import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.entity.DailyLog;
import com.poop_tracker.entity.User;

import java.util.List;

public class UserMapper {
    public static UserDTO mapToUserDto(User user) {
        System.out.println("mapToUserDto-======== user id: " + user.getId() + " user name: " + user.getName()) ;
        UserDTO userDto =  new UserDTO();
        System.out.println("userDto " + userDto);
        userDto.setId(user.getId());
        System.out.println("userDto id" + userDto.getId());
        userDto.setName(user.getName());
        System.out.println("userDto name" + userDto.getName());
        List<DailyLog> logs = user.getLogs();
        if(logs != null) {
            userDto.setLogs(logs.stream().map((log)-> DailyLogMapper.mapToDailyLogDto(log)).toList());
        }
        System.out.println("after calling logs mapper mapToUserDto-========");

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
        user.setSafeFoodList(userDto.getSafeFoodList());
        user.setAvoidFoodList(userDto.getAvoidFoodList());

        return user;
    }
}
