package com.poop_tracker.utils;

import com.poop_tracker.entity.Color;
import com.poop_tracker.entity.Poop;
import com.poop_tracker.entity.Size;
import com.poop_tracker.entity.Softness;

import static com.poop_tracker.entity.Softness.HARD;

public class PoopUtil {
    public static int calculateScore(Poop poop) {
        return getSizeScore(poop.getSize())+getColorScore(poop.getColor())+getSoftnessScore(poop.getSoftness());
    }

    private static int getSizeScore(Size size) {
        return switch(size) {
            case VERY_SMALL -> 1;
            case SMALL -> 2;
            case NORMAL, GINORMOUS -> 3;
            case BIG -> 4;
        };
    }

    private static int getColorScore(Color color) {
        return switch(color) {
           case LIGHT_BROWN -> 3;
           case BROWN -> 4;
           case DARK_BROWN -> 3;
           case PITCH_BLACK ->2;
           case RED -> -5;
        };
    }

    private static int getSoftnessScore(Softness softness) {
        return switch(softness) {
            case  WATERY -> 0;
            case SOFT, HARD -> 3;
            case NORMAL -> 4;
            case ROCK -> 2;
        };
    }
}
