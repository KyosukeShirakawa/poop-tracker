package com.poop_tracker.service.impl;

import com.poop_tracker.entity.DailyLog;
import com.poop_tracker.entity.User;
import com.poop_tracker.exception.ResourceNotFoundException;
import com.poop_tracker.repository.DailyLogRepository;
import com.poop_tracker.repository.UserRepository;
import com.poop_tracker.service.IDailyLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public DailyLog createDailyLog(Long userId, DailyLog dailyLog) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with Id: "+ userId));

        user.getLogs().add(dailyLog);
        dailyLog.setUser(user);
        userRepository.save(user);

        return dailyLogRepository.save(dailyLog);
    }
}
