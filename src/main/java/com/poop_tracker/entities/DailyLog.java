package com.poop_tracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "daily_log")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DailyLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "log")
    private Poop poop;

    private LocalDate date;

    @ManyToMany
    @JoinTable(
        name = "foods_eaten",
        joinColumns = @JoinColumn(name = "dailylog_id"),
        inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private Set<Food> foodsEaten;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
