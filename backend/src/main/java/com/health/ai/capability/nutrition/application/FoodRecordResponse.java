package com.health.ai.capability.nutrition.application;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FoodRecordResponse {

    private Long id;
    private String foodName;
    private BigDecimal calories;
    private BigDecimal proteinG;
    private BigDecimal carbsG;
    private BigDecimal fatG;
    private LocalDateTime recordedAt;
}
