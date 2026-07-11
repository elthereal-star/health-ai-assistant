package com.health.ai.capability.exercise.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ExerciseCalorieCalculator {

    public BigDecimal calculateBurnedCalories(ExerciseDictionary exercise, Integer durationMinutes) {
        return exercise.getCaloriesPerMinute()
                .multiply(new BigDecimal(durationMinutes))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
