package com.health.ai.capability.exercise.application;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExerciseRecordResponse {

    private Long id;
    private String exerciseType;
    private Integer durationMinutes;
    private BigDecimal caloriesBurned;
    private LocalDateTime recordedAt;
}
