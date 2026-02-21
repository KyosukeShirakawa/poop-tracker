package com.poop_tracker.mapper;

import com.poop_tracker.dto.DailyLogDto;
import com.poop_tracker.dto.PoopDTO;
import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.entity.DailyLog;
import com.poop_tracker.entity.Poop;
import com.poop_tracker.entity.User;

public class DailyLogMapper {
    public static DailyLogDto mapToDailyLogDto(DailyLog dailyLog) {
        DailyLogDto dailyLogDto = new DailyLogDto();

        UserDTO userDTO = UserMapper.mapToUserDto(dailyLog.getUser());
        PoopDTO poopDTO = PoopMapper.mapToPoopDto(dailyLog.getPoop());

        dailyLogDto.setUserDTO(userDTO);
        dailyLogDto.setPoopDTO(poopDTO);
        dailyLogDto.setDate(dailyLog.getDate());
        dailyLogDto.setFoodsEaten(dailyLog.getFoodsEaten());

        return dailyLogDto;
    }
    public static DailyLog mapToDailyLog(DailyLogDto dailyLogDto) {
        DailyLog dailyLog = new DailyLog();

        User user = dailyLog.getUser();
        Poop poop = dailyLog.getPoop();

        dailyLog.setUser(user);
        dailyLog.setPoop(poop);
        dailyLog.setDate(dailyLogDto.getDate());
        dailyLog.setFoodsEaten(dailyLogDto.getFoodsEaten());

        return dailyLog;
    }
}
