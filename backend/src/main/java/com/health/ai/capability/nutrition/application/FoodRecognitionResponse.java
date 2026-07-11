package com.health.ai.capability.nutrition.application;

import lombok.Data;

@Data
public class FoodRecognitionResponse {

    private String foodName;
    private Double suggestedPortion;
    private String suggestedUnit;
    private Double confidence;
}
