package com.poop_tracker.service;

import com.poop_tracker.dto.DailyLogDto;
import com.poop_tracker.dto.PoopDTO;
import com.poop_tracker.entity.DailyLog;

import java.time.LocalDate;
import java.util.List;

public interface IDailyLogService {
    List<DailyLog> getAllDailyLogs(Long userId);
    DailyLog getDailyLogByDate(Long userId, LocalDate date);
    DailyLog createDailyLog(Long userId);
    String deleteDailyLog(Long userId, Long logId);
    DailyLogDto recordPoopToDailyLog(Long userId, Long LogId, PoopDTO poopDTO);

}
