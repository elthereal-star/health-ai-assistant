package com.health.ai.capability.nutrition.application;

import lombok.Data;

@Data
public class FoodRecognitionRequest {

    private String text;
    private String imageUrl;
}
