package com.health.ai.capability.nutrition.infrastructure;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.health.ai.capability.nutrition.domain.FoodDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FoodDictionaryMapper extends BaseMapper<FoodDictionary> {

    @Select("SELECT * FROM food_dict WHERE name = #{name} LIMIT 1")
    FoodDictionary findByName(@Param("name") String name);
}
