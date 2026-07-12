package com.health.ai.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider provider;

    @BeforeEach
    void setUp() throws Exception {
        provider = new JwtTokenProvider();
        setField(provider, "jwtSecret", "health-ai-assistant-jwt-secret-key-for-test");
        setField(provider, "jwtExpiration", 86400000L);
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void shouldGenerateValidToken() {
        String token = provider.generateToken(1L);
        assertNotNull(token);
        assertTrue(provider.validateToken(token));
        assertEquals(1L, provider.getUserIdFromToken(token));
    }

    @Test
    void shouldRejectInvalidToken() {
        assertFalse(provider.validateToken("invalid-token"));
    }
}
