package com.health.ai.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.ai.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeepSeekAIService implements AIService {

    private final AIProperties aiProperties;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public FoodRecognitionResult recognizeFood(String text, String imageUrl) {
        String prompt = buildFoodRecognitionPrompt(text);
        String response = callAI(prompt);
        return parseFoodRecognition(response);
    }

    @Override
    public HealthAdviceResult generateHealthAdvice(String context) {
        String prompt = buildHealthAdvicePrompt(context);
        String response = callAI(prompt);
        return parseHealthAdvice(response);
    }

    private String callAI(String prompt) {
        if (!Boolean.TRUE.equals(aiProperties.getEnabled()) || aiProperties.getApiKey() == null || aiProperties.getApiKey().isBlank()) {
            log.warn("AI is disabled or API key is missing");
            throw new BusinessException(40002, "AI service is not configured");
        }

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(aiProperties.getBaseUrl())
                .setConnectTimeout(Duration.ofSeconds(aiProperties.getTimeoutSeconds()))
                .build();

        Map<String, Object> message = Map.of("role", "user", "content", prompt);
        Map<String, Object> body = Map.of(
                "model", aiProperties.getModel(),
                "messages", List.of(message),
                "temperature", 0.2
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(aiProperties.getApiKey());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "/chat/completions",
                HttpMethod.POST,
                request,
                String.class
        );

        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return root.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e) {
            log.error("Failed to parse AI response", e);
            throw new BusinessException(40002, "Failed to parse AI response");
        }
    }

    private String buildFoodRecognitionPrompt(String text) {
        return String.format(
            "You are a food recognition assistant. Extract the food name and portion from the user input. " +
            "Return ONLY a JSON object with no markdown formatting: {\"foodName\":\"...\",\"suggestedPortion\":1,\"suggestedUnit\":\"...\",\"confidence\":0.95}. " +
            "User input: %s",
            text
        );
    }

    private String buildHealthAdvicePrompt(String context) {
        return String.format(
            "You are a health advisor. Based on the following context, provide concise, practical advice. " +
            "Return ONLY a JSON object: {\"advice\":\"...\",\"suggestedActions\":[\"...\"]}. " +
            "Context: %s",
            context
        );
    }

    private FoodRecognitionResult parseFoodRecognition(String response) {
        try {
            String cleaned = response.trim().replaceAll("^```json\\s*", "").replaceAll("\\s*```$", "");
            return objectMapper.readValue(cleaned, FoodRecognitionResult.class);
        } catch (Exception e) {
            log.error("Failed to parse food recognition response: {}", response, e);
            throw new BusinessException(40002, "Failed to parse food recognition");
        }
    }

    private HealthAdviceResult parseHealthAdvice(String response) {
        try {
            String cleaned = response.trim().replaceAll("^```json\\s*", "").replaceAll("\\s*```$", "");
            return objectMapper.readValue(cleaned, HealthAdviceResult.class);
        } catch (Exception e) {
            log.error("Failed to parse health advice response: {}", response, e);
            throw new BusinessException(40002, "Failed to parse health advice");
        }
    }
}
