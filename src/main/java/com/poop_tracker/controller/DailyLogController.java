package com.poop_tracker.controller;

import com.poop_tracker.dto.DailyLogDto;
import com.poop_tracker.dto.PoopDTO;
import com.poop_tracker.entity.*;
import com.poop_tracker.service.impl.DailyLogServiceImpl;
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
    public ResponseEntity<List<DailyLogDto>> getAllDailyLogs(@PathVariable Long userId) {
        return ResponseEntity.ok(dailyLogService.getAllDailyLogs(userId));
    }

    @GetMapping("/{date}")
    public ResponseEntity<DailyLogDto> getDailyLogByDate(@PathVariable Long userId, @PathVariable LocalDate date) {
        return ResponseEntity.ok(dailyLogService.getDailyLogByDate(userId, date));
    }

    @PostMapping
    public ResponseEntity<DailyLogDto> createDailyLog(@PathVariable Long userId) {
        return ResponseEntity.ok(dailyLogService.createDailyLog(userId));
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<String> deleteDailyLog(@PathVariable Long userId, @PathVariable Long logId) {
        return ResponseEntity.ok(dailyLogService.deleteDailyLog(userId, logId));
    }

    @PostMapping("/{logId}")
    public ResponseEntity<DailyLogDto> recordPoopToDailyLog(@PathVariable Long userId, @PathVariable Long logId, @RequestBody PoopDTO poopDTO) {
        return ResponseEntity.ok(dailyLogService.recordPoopToDailyLog(userId, logId, poopDTO));
    }

    @PostMapping("/{logId}/foods")
    public ResponseEntity<DailyLogDto> addFoodsToDailylog(@PathVariable Long userId, @PathVariable Long logId, @RequestBody List<String> foods) {
        return ResponseEntity.ok(dailyLogService.addFoodsToLog(userId, logId, foods));
    }


}
