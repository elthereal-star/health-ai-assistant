package com.health.ai.capability.exercise.infrastructure;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.health.ai.capability.exercise.domain.ExerciseDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExerciseDictionaryMapper extends BaseMapper<ExerciseDictionary> {

    @Select("SELECT * FROM exercise_dict WHERE name = #{name} OR code = #{name} LIMIT 1")
    ExerciseDictionary findByNameOrCode(@Param("name") String name);
}
