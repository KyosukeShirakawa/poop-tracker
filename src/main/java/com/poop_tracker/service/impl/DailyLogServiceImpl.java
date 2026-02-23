package com.poop_tracker.service.impl;

import com.poop_tracker.dto.DailyLogDto;
import com.poop_tracker.dto.PoopDTO;
import com.poop_tracker.entity.DailyLog;
import com.poop_tracker.entity.Poop;
import com.poop_tracker.entity.User;
import com.poop_tracker.exception.ResourceNotFoundException;
import com.poop_tracker.mapper.DailyLogMapper;
import com.poop_tracker.mapper.PoopMapper;
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
    public List<DailyLogDto> getAllDailyLogs(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with Id: " + userId));

        List<DailyLog> logs = user.getLogs();


        return logs.stream().map(log -> DailyLogMapper.mapToDailyLogDto(log)).toList();
    }

    @Override
    public DailyLogDto getDailyLogByDate(Long userId, LocalDate date) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + userId));
        List<DailyLog> logs = user.getLogs();
        if(logs.isEmpty()) {
            return null;
        }
        DailyLog matchedLog=  logs.stream()
                .filter(log-> log.getDate().isEqual(date))
                .findAny()
                .orElse(null);

        return DailyLogMapper.mapToDailyLogDto(matchedLog);
    }

    @Override
    @Transactional
    public DailyLogDto createDailyLog(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with Id: "+ userId));
        DailyLog dailyLog = new DailyLog(user);
        user.getLogs().add(dailyLog);
        dailyLog.setUser(user);
        userRepository.save(user);

        return DailyLogMapper.mapToDailyLogDto(dailyLog);
    }

    @Override
    public String deleteDailyLog(Long userId, Long logId) {
        DailyLog dailyLog = dailyLogRepository.findById(logId).orElseThrow(() -> new ResourceNotFoundException("Log not found with Id: " +  logId));
        if(!dailyLog.getUser().getId().equals(userId)) {
            return "User with ID: " + userId + " is not authorized to delete the log";
        }

        dailyLogRepository.deleteById(logId);


        return "Log is successfully deleted";
    }

    @Override
    public DailyLogDto recordPoopToDailyLog(Long userId, Long logId, PoopDTO poopDTO) {
        DailyLog dailyLog = dailyLogRepository.findById(logId).orElseThrow(() -> new ResourceNotFoundException("Log not found with Id: " + logId));

        if(!dailyLog.getUser().getId().equals(userId)) {
            return null;
        }

        Poop poop = PoopMapper.mapToPoop(poopDTO);

        dailyLog.setPoop(poop);
        poop.setLog(dailyLog);



        return DailyLogMapper.mapToDailyLogDto(dailyLogRepository.save(dailyLog));
    }
}
