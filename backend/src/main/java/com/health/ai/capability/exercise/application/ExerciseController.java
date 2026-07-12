package com.health.ai.capability.exercise.application;

import com.health.ai.capability.exercise.domain.ExerciseRecord;
import com.health.ai.capability.exercise.service.ExerciseService;
import com.health.ai.shared.Result;
import com.health.ai.shared.security.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/exercise-records")
    public Result<ExerciseRecordResponse> createExerciseRecord(@Valid @RequestBody ExerciseRecordRequest request) {
        ExerciseRecord record = exerciseService.createExerciseRecord(
                CurrentUser.getId(),
                request.getExerciseType(),
                request.getDurationMinutes(),
                request.getRecordedAt()
        );
        return Result.ok(toResponse(record));
    }

    @GetMapping("/exercise-records")
    public Result<List<ExerciseRecordResponse>> listExerciseRecords(@RequestParam(required = false) LocalDate date) {
        List<ExerciseRecord> records = exerciseService.listByUserId(CurrentUser.getId(), date);
        return Result.ok(records.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    private ExerciseRecordResponse toResponse(ExerciseRecord record) {
        ExerciseRecordResponse response = new ExerciseRecordResponse();
        response.setId(record.getId());
        response.setExerciseType(record.getExerciseType());
        response.setDurationMinutes(record.getDurationMinutes());
        response.setCaloriesBurned(record.getCaloriesBurned());
        response.setRecordedAt(record.getRecordedAt());
        return response;
    }
}
