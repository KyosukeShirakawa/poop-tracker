package com.poop_tracker.service.impl;

import com.poop_tracker.dto.DailyLogDto;
import com.poop_tracker.dto.PoopDTO;
import com.poop_tracker.entity.DailyLog;
import com.poop_tracker.entity.Food;
import com.poop_tracker.entity.Poop;
import com.poop_tracker.entity.User;
import com.poop_tracker.exception.ResourceNotFoundException;
import com.poop_tracker.mapper.DailyLogMapper;
import com.poop_tracker.mapper.PoopMapper;
import com.poop_tracker.repository.DailyLogRepository;
import com.poop_tracker.repository.FoodRepository;
import com.poop_tracker.repository.UserRepository;
import com.poop_tracker.service.IDailyLogService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DailyLogServiceImpl implements IDailyLogService {
    DailyLogRepository dailyLogRepository;
    UserRepository userRepository;
    FoodRepository foodRepository;

    @Override
    public List<DailyLogDto> getAllDailyLogs(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with Id: " + userId));

        List<DailyLog> logs = user.getLogs();


        return logs.stream().map(log -> DailyLogMapper.mapToDailyLogDto(log)).toList();
    }

    @Override
    public DailyLogDto getDailyLogByDate(Long userId, LocalDate date) {
        return dailyLogRepository.findByUserIdAndDate(userId, date)
                .map((opt) -> DailyLogMapper.mapToDailyLogDto(opt))
                .orElseThrow(() -> new ResourceNotFoundException("Log not found with date : " + date));
    }

    @Override
    @Transactional
    public DailyLogDto createDailyLog(Long userId, DailyLogDto dailyLogDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with Id: "+ userId));


        List<Food> foods = new ArrayList<>();

        for (Food f : dailyLogDto.getFoodsEaten()) {
            if(f.getId() != null) {

            }
        }

        List<String> foodnames = dailyLogDto.getFoodsEaten().stream().map( food -> food.getName().trim().toLowerCase()).toList();
        List<Food> foodObjToSave = foodnames.stream().map(food ->
                foodRepository.findByName(food)
                        .orElseGet(() -> foodRepository.save(new Food(food)))
                ).toList();

        DailyLog dailyLog = DailyLogMapper.mapToDailyLog(dailyLogDto);
        foodObjToSave.forEach((food) ->dailyLog.getFoodsEaten().add(food));

        user.getLogs().add(dailyLog);
        dailyLog.setUser(user);
        userRepository.save(user);

        return DailyLogMapper.mapToDailyLogDto(dailyLog);
    }

    @Override
    public DailyLogDto updateDailyLog(Long userId, Long logId, DailyLogDto updatedLogDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with Id: "+ userId));

        DailyLog dailyLog = dailyLogRepository.findById(logId).orElseThrow(() -> new ResourceNotFoundException("Log not found with id: " + logId));

        List<String> foodnames = updatedLogDto.getFoodsEaten().stream().map( food -> food.getName().trim().toLowerCase()).toList();

        List<Food> foodObjToSave = foodnames.stream()
                .map(food ->
                    foodRepository.findByName(food)
                        .orElseGet(() -> foodRepository.save(new Food(food)))
                ).toList();
        dailyLog.getFoodsEaten().clear();
        dailyLog.getFoodsEaten().addAll(foodObjToSave);
        if(updatedLogDto.getPoopDTO() != null) {
            //poop update is coming
            Poop poop = dailyLog.getPoop();
            PoopDTO dto = updatedLogDto.getPoopDTO();

            if (poop == null) {
                // creating new poop if it wasnt set
                poop = new Poop();
                poop.setLog(dailyLog);
                dailyLog.setPoop(poop);
            }
            poop.setSize(dto.getSize());
            poop.setColor(dto.getColor());
            poop.setSoftness(dto.getSoftness());

        } else {
            dailyLog.setPoop(null);
        }

        dailyLogRepository.save(dailyLog);

        return DailyLogMapper.mapToDailyLogDto(dailyLog);    }

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

    @Override
    public DailyLogDto addFoodsToLog(Long userId, Long logId, List<String> foods) {
        DailyLog dailyLog = dailyLogRepository.findById(logId).orElseThrow(() -> new ResourceNotFoundException("Log not found with Id: " +  logId));
        if(!dailyLog.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to add food to the log");
        }

        List<String> foodnames = foods.stream().map( food -> food.trim().toLowerCase()).toList();

        List<Food> foodObjToSave = foodnames.stream().map(food ->
            foodRepository.findByName(food)
                    .orElseGet(() -> foodRepository.save(new Food(food)))
        ).toList();

        foodObjToSave.forEach((food) ->dailyLog.getFoodsEaten().add(food) );

        return DailyLogMapper.mapToDailyLogDto(dailyLogRepository.save(dailyLog));
    }
}
