package com.health.ai.capability.exercise.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExerciseRecordRequest {

    @NotBlank(message = "exerciseType is required")
    private String exerciseType;

    @NotNull(message = "durationMinutes is required")
    private Integer durationMinutes;

    private LocalDateTime recordedAt;
}
