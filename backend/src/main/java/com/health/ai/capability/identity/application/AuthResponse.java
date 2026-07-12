package com.health.ai.capability.identity.application;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String tokenType;
    private Long userId;
    private String username;
}
