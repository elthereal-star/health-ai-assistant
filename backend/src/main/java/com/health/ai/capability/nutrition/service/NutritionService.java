package com.health.ai.capability.nutrition.service;

import com.health.ai.capability.nutrition.domain.FoodDictionary;
import com.health.ai.capability.nutrition.domain.FoodRecord;
import com.health.ai.capability.nutrition.domain.NutritionCalculator;
import com.health.ai.capability.nutrition.infrastructure.FoodDictionaryMapper;
import com.health.ai.capability.nutrition.infrastructure.FoodRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NutritionService {

    private final FoodRecordMapper foodRecordMapper;
    private final FoodDictionaryMapper foodDictionaryMapper;
    private final NutritionCalculator nutritionCalculator;

    public FoodRecord createFoodRecord(Long userId, String foodName, BigDecimal portion, String unit, LocalDateTime recordedAt) {
        FoodDictionary food = foodDictionaryMapper.findByName(foodName);
        if (food == null) {
            food = foodDictionaryMapper.selectById(1L);
        }

        BigDecimal portionInGrams = convertToGrams(portion, unit, food);

        FoodRecord record = new FoodRecord();
        record.setUserId(userId);
        record.setFoodName(foodName);
        record.setStandardFoodCode(food.getCode());
        record.setPortion(portion);
        record.setUnit(unit);
        record.setCalories(nutritionCalculator.calculateCalories(food, portionInGrams));
        record.setProteinG(nutritionCalculator.calculateProtein(food, portionInGrams));
        record.setCarbsG(nutritionCalculator.calculateCarbs(food, portionInGrams));
        record.setFatG(nutritionCalculator.calculateFat(food, portionInGrams));
        record.setRecordedAt(recordedAt == null ? LocalDateTime.now() : recordedAt);
        record.setSource("MANUAL");

        foodRecordMapper.insert(record);
        return record;
    }

    private BigDecimal convertToGrams(BigDecimal portion, String unit, FoodDictionary food) {
        if ("g".equalsIgnoreCase(unit)) {
            return portion;
        }
        if (food.getDefaultUnit() != null && food.getDefaultUnit().equalsIgnoreCase(unit)) {
            return food.getDefaultPortion().multiply(portion);
        }
        return portion.multiply(new BigDecimal("100"));
    }
}
