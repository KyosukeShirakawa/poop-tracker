package com.poop_tracker.dto;

import com.poop_tracker.entity.DailyLog;
import com.poop_tracker.entity.Food;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String name;
    private List<DailyLogDto> logs;
    private Set<Food> safeFoodList;
    private Set<Food> avoidFoodList;
}
