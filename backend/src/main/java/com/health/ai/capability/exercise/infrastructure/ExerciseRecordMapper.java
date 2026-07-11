package com.health.ai.capability.exercise.infrastructure;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.health.ai.capability.exercise.domain.ExerciseRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExerciseRecordMapper extends BaseMapper<ExerciseRecord> {
}
