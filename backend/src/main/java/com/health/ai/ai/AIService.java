package com.health.ai.ai;

public interface AIService {

    FoodRecognitionResult recognizeFood(String text, String imageUrl);

    HealthAdviceResult generateHealthAdvice(String context);
}
