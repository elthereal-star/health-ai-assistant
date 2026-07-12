package com.health.ai.capability.identity.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("user_profile")
public class UserProfile {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String gender;
    private LocalDate birthDate;
    private Integer heightCm;
    private BigDecimal weightKg;
    private Integer dailyCalorieGoal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
