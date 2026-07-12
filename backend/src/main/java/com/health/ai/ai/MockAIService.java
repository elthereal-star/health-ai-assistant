package com.health.ai.ai;

import com.health.ai.shared.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MockAIService implements AIService {

    private static final Map<String, FoodRecognitionResult> FOOD_KNOWLEDGE = new HashMap<>();

    static {
        putFood("beef noodles", "beef noodles", 1.0, "bowl", 0.95);
        putFood("noodles", "beef noodles", 1.0, "bowl", 0.85);
        putFood("牛肉面", "beef noodles", 1.0, "bowl", 0.95);
        putFood("rice", "rice", 1.0, "bowl", 0.95);
        putFood("米饭", "rice", 1.0, "bowl", 0.95);
        putFood("egg", "egg", 1.0, "piece", 0.95);
        putFood("鸡蛋", "egg", 1.0, "piece", 0.95);
        putFood("apple", "apple", 1.0, "piece", 0.95);
        putFood("苹果", "apple", 1.0, "piece", 0.95);
        putFood("chicken breast", "chicken breast", 100.0, "g", 0.95);
        putFood("鸡胸肉", "chicken breast", 100.0, "g", 0.95);
        putFood("running", "running", 30.0, "minutes", 0.95);
        putFood("跑步", "running", 30.0, "minutes", 0.95);
    }

    private static void putFood(String keyword, String foodName, double portion, String unit, double confidence) {
        FoodRecognitionResult result = new FoodRecognitionResult();
        result.setFoodName(foodName);
        result.setSuggestedPortion(portion);
        result.setSuggestedUnit(unit);
        result.setConfidence(confidence);
        FOOD_KNOWLEDGE.put(keyword, result);
    }

    @Override
    public FoodRecognitionResult recognizeFood(String text, String imageUrl) {
        if (text == null || text.isBlank()) {
            throw new BusinessException(40001, "Text input is required");
        }
        String lower = text.toLowerCase();
        for (Map.Entry<String, FoodRecognitionResult> entry : FOOD_KNOWLEDGE.entrySet()) {
            if (lower.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        FoodRecognitionResult fallback = new FoodRecognitionResult();
        fallback.setFoodName("rice");
        fallback.setSuggestedPortion(1.0);
        fallback.setSuggestedUnit("bowl");
        fallback.setConfidence(0.60);
        return fallback;
    }

    @Override
    public HealthAdviceResult generateHealthAdvice(String context) {
        HealthAdviceResult result = new HealthAdviceResult();
        result.setAdvice("Keep a balanced diet and regular exercise. Track your intake daily and maintain a sustainable calorie plan.");
        result.setSuggestedActions(List.of(
            "Record your meals consistently",
            "Exercise at least 30 minutes a day",
            "Drink plenty of water",
            "Get 7-8 hours of sleep"
        ));
        return result;
    }
}
