package com.poop_tracker.mapper;

import com.poop_tracker.dto.DailyLogDto;
import com.poop_tracker.dto.PoopDTO;
import com.poop_tracker.dto.UserDTO;
import com.poop_tracker.entity.DailyLog;
import com.poop_tracker.entity.Poop;
import com.poop_tracker.entity.User;

public class DailyLogMapper {
    public static DailyLogDto mapToDailyLogDto(DailyLog dailyLog) {
        if(dailyLog==null) {
            return null;
        }
        DailyLogDto dailyLogDto = new DailyLogDto();

        PoopDTO poopDTO = PoopMapper.mapToPoopDto(dailyLog.getPoop());

        dailyLogDto.setUserId(dailyLog.getUser().getId());
        dailyLogDto.setPoopDTO(poopDTO);
        dailyLogDto.setDate(dailyLog.getDate());
        dailyLogDto.setFoodsEaten(dailyLog.getFoodsEaten());

        return dailyLogDto;
    }
    public static DailyLog mapToDailyLog(DailyLogDto dailyLogDto) {
        if (dailyLogDto==null) {
            return null;
        }
        DailyLog dailyLog = new DailyLog();
        Poop poop = dailyLog.getPoop();
        dailyLog.setPoop(poop);
        dailyLog.setDate(dailyLogDto.getDate());
        dailyLog.setFoodsEaten(dailyLogDto.getFoodsEaten());

        return dailyLog;
    }
}
