package com.health.ai.capability.nutrition.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NutritionCalculatorTest {

    @Test
    void shouldCalculateCalories() {
        FoodDictionary food = new FoodDictionary();
        food.setCaloriesPer100G(new BigDecimal("100"));

        NutritionCalculator calculator = new NutritionCalculator();
        BigDecimal result = calculator.calculateCalories(food, new BigDecimal("200"));

        assertEquals(new BigDecimal("200.00"), result);
    }

    @Test
    void shouldCalculateProtein() {
        FoodDictionary food = new FoodDictionary();
        food.setProteinPer100G(new BigDecimal("20"));

        NutritionCalculator calculator = new NutritionCalculator();
        BigDecimal result = calculator.calculateProtein(food, new BigDecimal("100"));

        assertEquals(new BigDecimal("20.00"), result);
    }
}
