package com.health.ai.capability.nutrition.application;

import com.health.ai.ai.AIService;
import com.health.ai.ai.FoodRecognitionResult;
import com.health.ai.capability.nutrition.domain.FoodRecord;
import com.health.ai.capability.nutrition.infrastructure.FoodRecordMapper;
import com.health.ai.capability.nutrition.service.NutritionService;
import com.health.ai.shared.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nutrition")
@RequiredArgsConstructor
public class NutritionController {

    private final FoodRecordMapper foodRecordMapper;
    private final NutritionService nutritionService;
    private final AIService aiService;

    @PostMapping("/food-records")
    public Result<FoodRecordResponse> createFoodRecord(@Valid @RequestBody FoodRecordRequest request) {
        FoodRecord record = nutritionService.createFoodRecord(
                1L,
                request.getFoodName(),
                request.getPortion(),
                request.getUnit(),
                request.getRecordedAt()
        );
        return Result.ok(toResponse(record));
    }

    @GetMapping("/food-records")
    public Result<List<FoodRecordResponse>> listFoodRecords(@RequestParam(required = false) String date) {
        List<FoodRecord> records = foodRecordMapper.selectList(null);
        List<FoodRecordResponse> responses = records.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return Result.ok(responses);
    }

    @PostMapping("/food-records/recognize")
    public Result<FoodRecognitionResponse> recognizeFood(@RequestBody FoodRecognitionRequest request) {
        FoodRecognitionResult result = aiService.recognizeFood(request.getText(), request.getImageUrl());
        FoodRecognitionResponse response = new FoodRecognitionResponse();
        response.setFoodName(result.getFoodName());
        response.setSuggestedPortion(result.getSuggestedPortion());
        response.setSuggestedUnit(result.getSuggestedUnit());
        response.setConfidence(result.getConfidence());
        return Result.ok(response);
    }

    private FoodRecordResponse toResponse(FoodRecord record) {
        FoodRecordResponse response = new FoodRecordResponse();
        response.setId(record.getId());
        response.setFoodName(record.getFoodName());
        response.setCalories(record.getCalories());
        response.setProteinG(record.getProteinG());
        response.setCarbsG(record.getCarbsG());
        response.setFatG(record.getFatG());
        response.setRecordedAt(record.getRecordedAt());
        return response;
    }
}
