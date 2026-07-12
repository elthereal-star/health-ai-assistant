package com.health.ai.capability.nutrition.infrastructure;

import java.time.LocalDate;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.health.ai.capability.nutrition.domain.FoodRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FoodRecordMapper extends BaseMapper<FoodRecord> {

    @Select("SELECT * FROM food_record WHERE user_id = #{userId} AND DATE(recorded_at) BETWEEN #{start} AND #{end} ORDER BY recorded_at DESC")
    List<FoodRecord> selectByUserIdAndDateRange(@Param("userId") Long userId, @Param("start") LocalDate start, @Param("end") LocalDate end);
}
