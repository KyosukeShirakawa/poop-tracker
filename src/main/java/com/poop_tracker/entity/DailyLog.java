package com.poop_tracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "daily_log")
@NoArgsConstructor
@Data
public class DailyLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "log", cascade = CascadeType.ALL)
    private Poop poop;

    @CreationTimestamp
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
    @JsonIgnore
    private User user;

    public DailyLog(User user) {
        if(user==null) {
            throw new IllegalArgumentException("User is not defined");
        }

        this.user = user;
        foodsEaten = new HashSet<>();
    }
}
