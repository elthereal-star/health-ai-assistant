package com.health.ai.capability.identity.infrastructure;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.health.ai.capability.identity.domain.UserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserProfileMapper extends BaseMapper<UserProfile> {

    @Select("SELECT * FROM user_profile WHERE user_id = #{userId}")
    UserProfile findByUserId(@Param("userId") Long userId);
}
