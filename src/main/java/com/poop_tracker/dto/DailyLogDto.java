package com.poop_tracker.dto;

import com.poop_tracker.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DailyLogDto {
    private Long id;
    private LocalDate date;
    private PoopDTO poopDTO;
    private Set<Food> foodsEaten;
}
