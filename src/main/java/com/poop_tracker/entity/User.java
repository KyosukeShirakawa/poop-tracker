package com.poop_tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DailyLog> logs;

    @ManyToMany
    @JoinTable(
            name = "safe_food_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private Set<Food> safeFoodList;

    @ManyToMany
    @JoinTable(
            name = "avoid_food_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private Set<Food> avoidFoodList;


    public User(String name) {
        logs = new ArrayList<>();
        safeFoodList = new HashSet<>();
        avoidFoodList = new HashSet<>();
    }
}
