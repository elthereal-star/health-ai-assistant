package com.health.ai.capability.identity.service;

import com.health.ai.capability.identity.application.AuthResponse;
import com.health.ai.capability.identity.application.LoginRequest;
import com.health.ai.capability.identity.application.RegisterRequest;
import com.health.ai.capability.identity.domain.User;
import com.health.ai.capability.identity.infrastructure.UserMapper;
import com.health.ai.capability.identity.infrastructure.UserProfileMapper;
import com.health.ai.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IdentityServiceTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private UserProfileMapper userProfileMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private IdentityService identityService;

    @Test
    void shouldRegisterNewUser() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("test");
        request.setEmail("test@example.com");
        request.setPassword("password");

        when(userMapper.findByUsername("test")).thenReturn(null);
        when(userMapper.findByEmail("test@example.com")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("hashed");
        when(jwtTokenProvider.generateToken(any())).thenReturn("token");
        doAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(1L);
            return 0;
        }).when(userMapper).insert(any(User.class));

        AuthResponse response = identityService.register(request);

        assertNotNull(response);
        assertEquals("token", response.getToken());
        assertEquals("test", response.getUsername());
        assertEquals(1L, response.getUserId());
    }

    @Test
    void shouldLoginWithValidCredentials() {
        LoginRequest request = new LoginRequest();
        request.setUsername("test");
        request.setPassword("password");

        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPasswordHash("hashed");

        when(userMapper.findByUsername("test")).thenReturn(user);
        when(passwordEncoder.matches("password", "hashed")).thenReturn(true);
        when(jwtTokenProvider.generateToken(1L)).thenReturn("token");

        AuthResponse response = identityService.login(request);

        assertNotNull(response);
        assertEquals("token", response.getToken());
        assertEquals("test", response.getUsername());
    }
}
