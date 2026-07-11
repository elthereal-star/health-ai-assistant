package com.health.ai.capability.nutrition.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("food_dict")
public class FoodDictionary {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String code;
    private String name;
    private BigDecimal caloriesPer100G;
    private BigDecimal proteinPer100G;
    private BigDecimal carbsPer100G;
    private BigDecimal fatPer100G;
    private String defaultUnit;
    private BigDecimal defaultPortion;
}
