package com.health.ai.capability.exercise.service;

import com.health.ai.capability.exercise.domain.ExerciseCalorieCalculator;
import com.health.ai.capability.exercise.domain.ExerciseDictionary;
import com.health.ai.capability.exercise.domain.ExerciseRecord;
import com.health.ai.capability.exercise.infrastructure.ExerciseDictionaryMapper;
import com.health.ai.capability.exercise.infrastructure.ExerciseRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRecordMapper exerciseRecordMapper;
    private final ExerciseDictionaryMapper exerciseDictionaryMapper;
    private final ExerciseCalorieCalculator exerciseCalorieCalculator;

    public ExerciseRecord createExerciseRecord(Long userId, String exerciseType, Integer durationMinutes, LocalDateTime recordedAt) {
        ExerciseDictionary exercise = exerciseDictionaryMapper.findByNameOrCode(exerciseType);
        if (exercise == null) {
            exercise = exerciseDictionaryMapper.selectById(1L);
        }

        ExerciseRecord record = new ExerciseRecord();
        record.setUserId(userId);
        record.setExerciseType(exerciseType);
        record.setDurationMinutes(durationMinutes);
        record.setCaloriesBurned(exerciseCalorieCalculator.calculateBurnedCalories(exercise, durationMinutes));
        record.setRecordedAt(recordedAt == null ? LocalDateTime.now() : recordedAt);
        record.setCreatedAt(LocalDateTime.now());

        exerciseRecordMapper.insert(record);
        return record;
    }
}
