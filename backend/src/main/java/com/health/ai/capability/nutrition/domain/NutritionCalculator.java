package com.health.ai.capability.nutrition.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class NutritionCalculator {

    public BigDecimal calculateCalories(FoodDictionary food, BigDecimal portionInGrams) {
        return food.getCaloriesPer100G()
                .multiply(portionInGrams)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateProtein(FoodDictionary food, BigDecimal portionInGrams) {
        return food.getProteinPer100G()
                .multiply(portionInGrams)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateCarbs(FoodDictionary food, BigDecimal portionInGrams) {
        return food.getCarbsPer100G()
                .multiply(portionInGrams)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateFat(FoodDictionary food, BigDecimal portionInGrams) {
        return food.getFatPer100G()
                .multiply(portionInGrams)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }
}
