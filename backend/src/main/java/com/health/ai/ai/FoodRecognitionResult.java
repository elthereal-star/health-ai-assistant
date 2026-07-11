package com.health.ai.ai;

import lombok.Data;

@Data
public class FoodRecognitionResult {

    private String foodName;
    private Double suggestedPortion;
    private String suggestedUnit;
    private Double confidence;
}
