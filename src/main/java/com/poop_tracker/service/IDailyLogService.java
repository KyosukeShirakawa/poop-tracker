package com.poop_tracker.service;

import com.poop_tracker.dto.DailyLogDto;
import com.poop_tracker.dto.PoopDTO;

import java.time.LocalDate;
import java.util.List;

public interface IDailyLogService {
    List<DailyLogDto> getAllDailyLogs(Long userId);
    DailyLogDto getDailyLogByDate(Long userId, LocalDate date);
    DailyLogDto createDailyLog(Long userId);
    String deleteDailyLog(Long userId, Long logId);
    DailyLogDto recordPoopToDailyLog(Long userId, Long logId, PoopDTO poopDTO);
    DailyLogDto addFoodsToLog(Long userId, Long logId, List<String> foods);
}
