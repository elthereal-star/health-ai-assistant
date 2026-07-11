package com.health.ai.capability.analytics.application;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DashboardResponse {

    private BigDecimal caloriesIn;
    private BigDecimal caloriesOut;
    private BigDecimal netCalories;
    private Integer goalCalories;
    private BigDecimal goalCompletionRate;
    private BigDecimal proteinG;
    private BigDecimal carbsG;
    private BigDecimal fatG;
    private List<TrendItem> last7Days;
}
