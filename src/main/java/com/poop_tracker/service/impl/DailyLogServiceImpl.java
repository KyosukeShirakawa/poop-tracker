package com.poop_tracker.service.impl;

import com.poop_tracker.entity.DailyLog;
import com.poop_tracker.entity.User;
import com.poop_tracker.exception.ResourceNotFoundException;
import com.poop_tracker.repository.DailyLogRepository;
import com.poop_tracker.repository.UserRepository;
import com.poop_tracker.service.IDailyLogService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DailyLogServiceImpl implements IDailyLogService {
    DailyLogRepository dailyLogRepository;
    UserRepository userRepository;

    @Override
    public List<DailyLog> getAllDailyLogs(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with Id: " + userId));
        return user.getLogs();
    }

    @Override
    public DailyLog getDailyLogByDate(Long userId, LocalDate date) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + userId));
        List<DailyLog> logs = user.getLogs();
        return logs.stream()
                .filter(log-> log.getDate().isEqual(date))
                .findAny()
                .orElse(null);
    }

    @Override
    @Transactional
    public DailyLog createDailyLog(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with Id: "+ userId));
        DailyLog dailyLog = new DailyLog(user);
        user.getLogs().add(dailyLog);
        dailyLog.setUser(user);
        userRepository.save(user);

        return dailyLog;
    }
}
