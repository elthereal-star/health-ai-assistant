package com.health.ai.capability.nutrition.infrastructure;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.health.ai.capability.nutrition.domain.FoodRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodRecordMapper extends BaseMapper<FoodRecord> {
}
