package com.health.ai.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AIFactory {

    private final DeepSeekAIService deepSeekAIService;
    private final MockAIService mockAIService;
    private final AIProperties aiProperties;

    public AIService getService() {
        if (Boolean.TRUE.equals(aiProperties.getEnabled())
                && aiProperties.getApiKey() != null
                && !aiProperties.getApiKey().isBlank()) {
            log.info("Using DeepSeek AI service");
            return deepSeekAIService;
        }
        log.info("Using Mock AI service");
        return mockAIService;
    }
}
