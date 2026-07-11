package com.health.ai.ai;

import com.health.ai.shared.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @PostMapping("/health-advice")
    public Result<HealthAdviceResult> healthAdvice(@RequestBody HealthAdviceRequest request) {
        return Result.ok(aiService.generateHealthAdvice(request.getContext()));
    }
}
