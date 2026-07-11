package com.health.ai.capability.nutrition.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FoodRecordRequest {

    @NotBlank(message = "foodName is required")
    private String foodName;

    @NotNull(message = "portion is required")
    private BigDecimal portion;

    @NotBlank(message = "unit is required")
    private String unit;

    private LocalDateTime recordedAt;
}
