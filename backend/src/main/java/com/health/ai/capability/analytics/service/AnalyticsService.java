package com.health.ai.capability.analytics.service;

import com.health.ai.capability.analytics.application.DashboardResponse;
import com.health.ai.capability.analytics.application.TrendItem;
import com.health.ai.capability.exercise.domain.ExerciseRecord;
import com.health.ai.capability.exercise.infrastructure.ExerciseRecordMapper;
import com.health.ai.capability.identity.domain.UserProfile;
import com.health.ai.capability.identity.infrastructure.UserProfileMapper;
import com.health.ai.capability.nutrition.domain.FoodRecord;
import com.health.ai.capability.nutrition.infrastructure.FoodRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final FoodRecordMapper foodRecordMapper;
    private final ExerciseRecordMapper exerciseRecordMapper;
    private final UserProfileMapper userProfileMapper;

    public DashboardResponse buildDashboard(Long userId) {
        LocalDate today = LocalDate.now();
        UserProfile profile = userProfileMapper.findByUserId(userId);
        int goalCalories = profile != null && profile.getDailyCalorieGoal() != null ? profile.getDailyCalorieGoal() : 2000;

        List<FoodRecord> foodRecords = foodRecordMapper.selectByUserIdAndDateRange(userId, today, today);
        List<ExerciseRecord> exerciseRecords = exerciseRecordMapper.selectByUserIdAndDateRange(userId, today, today);

        BigDecimal caloriesIn = sum(foodRecords, FoodRecord::getCalories);
        BigDecimal proteinG = sum(foodRecords, FoodRecord::getProteinG);
        BigDecimal carbsG = sum(foodRecords, FoodRecord::getCarbsG);
        BigDecimal fatG = sum(foodRecords, FoodRecord::getFatG);
        BigDecimal caloriesOut = sum(exerciseRecords, ExerciseRecord::getCaloriesBurned);

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
        response.setLast7Days(buildLast7DaysTrend(userId));
        return response;
    }

    private List<TrendItem> buildLast7DaysTrend(Long userId) {
        LocalDate today = LocalDate.now();
        List<TrendItem> trends = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            List<FoodRecord> food = foodRecordMapper.selectByUserIdAndDateRange(userId, date, date);
            List<ExerciseRecord> exercise = exerciseRecordMapper.selectByUserIdAndDateRange(userId, date, date);
            BigDecimal in = sum(food, FoodRecord::getCalories);
            BigDecimal out = sum(exercise, ExerciseRecord::getCaloriesBurned);
            BigDecimal net = in.subtract(out);
            TrendItem item = new TrendItem();
            item.setDate(date.toString());
            item.setNetCalories(net);
            trends.add(item);
        }
        return trends;
    }

    private <T> BigDecimal sum(List<T> items, Function<T, BigDecimal> mapper) {
        return items.stream().map(mapper).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
