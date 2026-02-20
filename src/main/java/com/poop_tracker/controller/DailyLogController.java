package com.poop_tracker.controller;

import com.poop_tracker.entity.DailyLog;
import com.poop_tracker.entity.User;
import com.poop_tracker.service.impl.DailyLogServiceImpl;
import com.poop_tracker.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<DailyLog> createDailyLog(@PathVariable Long userId, @RequestBody DailyLog dailyLog) {
        return ResponseEntity.ok(dailyLogService.createDailyLog(userId, dailyLog));
    }
}
