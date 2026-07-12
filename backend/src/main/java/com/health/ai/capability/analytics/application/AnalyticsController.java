package com.health.ai.capability.analytics.application;

import com.health.ai.capability.analytics.service.AnalyticsService;
import com.health.ai.shared.Result;
import com.health.ai.shared.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/dashboard")
    public Result<DashboardResponse> dashboard() {
        return Result.ok(analyticsService.buildDashboard(CurrentUser.getId()));
    }
}
