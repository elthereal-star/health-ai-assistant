package com.health.ai.capability.exercise.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("exercise_record")
public class ExerciseRecord {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String exerciseType;
    private Integer durationMinutes;
    private BigDecimal caloriesBurned;
    private LocalDateTime recordedAt;
    private LocalDateTime createdAt;
}
