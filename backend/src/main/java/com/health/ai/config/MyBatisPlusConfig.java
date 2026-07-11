package com.health.ai.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.health.ai.**.infrastructure")
public class MyBatisPlusConfig {
}
