package com.health.ai.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AIFactory {

    private final DeepSeekAIService deepSeekAIService;

    public AIService getService() {
        return deepSeekAIService;
    }
}
