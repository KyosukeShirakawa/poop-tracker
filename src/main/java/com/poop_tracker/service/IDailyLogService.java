package com.poop_tracker.service;

import com.poop_tracker.entity.DailyLog;

import java.util.List;

public interface IDailyLogService {
    List<DailyLog> getAllDailyLogs(Long userId);
    DailyLog createDailyLog(Long userId, DailyLog dailyLog);
}
