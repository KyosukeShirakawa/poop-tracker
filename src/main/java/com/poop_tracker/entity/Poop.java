package com.poop_tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Poop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Size size;
    private Color color;
    private Softness softness;
    @OneToOne
    @JoinColumn(name = "log_id", unique = true)
    @JsonIgnore
    private DailyLog log;
}
