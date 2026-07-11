package com.health.ai.ai;

import lombok.Data;
import java.util.List;

@Data
public class HealthAdviceResult {

    private String advice;
    private List<String> suggestedActions;
}
