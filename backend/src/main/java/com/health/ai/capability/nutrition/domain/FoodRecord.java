package com.health.ai.capability.nutrition.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("food_record")
public class FoodRecord {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String foodName;
    private String standardFoodCode;
    private BigDecimal portion;
    private String unit;
    private BigDecimal calories;
    private BigDecimal proteinG;
    private BigDecimal carbsG;
    private BigDecimal fatG;
    private LocalDateTime recordedAt;
    private String source;
    private LocalDateTime createdAt;
}
