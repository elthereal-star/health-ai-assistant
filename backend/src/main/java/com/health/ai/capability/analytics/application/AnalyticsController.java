package com.health.ai.capability.analytics.application;

import com.health.ai.capability.exercise.domain.ExerciseRecord;
import com.health.ai.capability.exercise.infrastructure.ExerciseRecordMapper;
import com.health.ai.capability.nutrition.domain.FoodRecord;
import com.health.ai.capability.nutrition.infrastructure.FoodRecordMapper;
import com.health.ai.shared.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final FoodRecordMapper foodRecordMapper;
    private final ExerciseRecordMapper exerciseRecordMapper;

    @GetMapping("/dashboard")
    public Result<DashboardResponse> dashboard() {
        Long userId = 1L;
        LocalDate today = LocalDate.now();

        List<FoodRecord> foodRecords = foodRecordMapper.selectList(null).stream()
                .filter(r -> r.getUserId().equals(userId))
                .filter(r -> r.getRecordedAt() != null && r.getRecordedAt().toLocalDate().equals(today))
                .collect(Collectors.toList());

        List<ExerciseRecord> exerciseRecords = exerciseRecordMapper.selectList(null).stream()
                .filter(r -> r.getUserId().equals(userId))
                .filter(r -> r.getRecordedAt() != null && r.getRecordedAt().toLocalDate().equals(today))
                .collect(Collectors.toList());

        BigDecimal caloriesIn = sum(foodRecords, FoodRecord::getCalories);
        BigDecimal proteinG = sum(foodRecords, FoodRecord::getProteinG);
        BigDecimal carbsG = sum(foodRecords, FoodRecord::getCarbsG);
        BigDecimal fatG = sum(foodRecords, FoodRecord::getFatG);
        BigDecimal caloriesOut = sum(exerciseRecords, ExerciseRecord::getCaloriesBurned);

        int goalCalories = 2000;
        BigDecimal netCalories = caloriesIn.subtract(caloriesOut);
        BigDecimal goalCompletionRate = caloriesIn.divide(new BigDecimal(goalCalories), 2, RoundingMode.HALF_UP);

        DashboardResponse response = new DashboardResponse();
        response.setCaloriesIn(caloriesIn);
        response.setCaloriesOut(caloriesOut);
        response.setNetCalories(netCalories);
        response.setGoalCalories(goalCalories);
        response.setGoalCompletionRate(goalCompletionRate);
        response.setProteinG(proteinG);
        response.setCarbsG(carbsG);
        response.setFatG(fatG);
        response.setLast7Days(List.of());

        return Result.ok(response);
    }

    private <T> BigDecimal sum(List<T> items, java.util.function.Function<T, BigDecimal> mapper) {
        return items.stream()
                .map(mapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
