package com.health.ai.capability.analytics.application;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TrendItem {

    private String date;
    private BigDecimal netCalories;
}
