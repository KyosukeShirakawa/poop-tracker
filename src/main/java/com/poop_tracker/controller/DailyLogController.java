package com.poop_tracker.controller;

import com.poop_tracker.entity.DailyLog;
import com.poop_tracker.entity.User;
import com.poop_tracker.service.impl.DailyLogServiceImpl;
import com.poop_tracker.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/logs")
@AllArgsConstructor
public class DailyLogController {
    DailyLogServiceImpl dailyLogService;
    @GetMapping
    public ResponseEntity<List<DailyLog>> getAllDailyLogs(@PathVariable Long userId) {
        return ResponseEntity.ok(dailyLogService.getAllDailyLogs(userId));
    }

    @GetMapping("/{date}")
    public ResponseEntity<DailyLog> getDailyLogByDate(@PathVariable Long userId, @PathVariable LocalDate date) {
        return ResponseEntity.ok(dailyLogService.getDailyLogByDate(userId, date));
    }

    @PostMapping
    public ResponseEntity<DailyLog> createDailyLog(@PathVariable Long userId) {
        return ResponseEntity.ok(dailyLogService.createDailyLog(userId));
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<String> deleteDailyLog(@PathVariable Long userId, @PathVariable Long logId) {
        return ResponseEntity.ok(dailyLogService.deleteDailyLog(userId, logId));
    }
}
