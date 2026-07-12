package com.health.ai.capability.exercise.infrastructure;

import java.time.LocalDate;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.health.ai.capability.exercise.domain.ExerciseRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExerciseRecordMapper extends BaseMapper<ExerciseRecord> {

    @Select("SELECT * FROM exercise_record WHERE user_id = #{userId} AND DATE(recorded_at) BETWEEN #{start} AND #{end} ORDER BY recorded_at DESC")
    List<ExerciseRecord> selectByUserIdAndDateRange(@Param("userId") Long userId, @Param("start") LocalDate start, @Param("end") LocalDate end);
}
