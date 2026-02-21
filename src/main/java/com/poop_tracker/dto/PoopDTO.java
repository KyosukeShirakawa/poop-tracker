package com.poop_tracker.dto;

import com.poop_tracker.entity.Color;
import com.poop_tracker.entity.Size;
import com.poop_tracker.entity.Softness;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PoopDTO {
    private Size size;
    private Color color;
    private Softness softness;
}
