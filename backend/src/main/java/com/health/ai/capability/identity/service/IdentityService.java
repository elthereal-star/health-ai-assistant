package com.health.ai.capability.identity.service;

import com.health.ai.capability.identity.application.AuthResponse;
import com.health.ai.capability.identity.application.LoginRequest;
import com.health.ai.capability.identity.application.RegisterRequest;
import com.health.ai.capability.identity.domain.User;
import com.health.ai.capability.identity.domain.UserProfile;
import com.health.ai.capability.identity.infrastructure.UserMapper;
import com.health.ai.capability.identity.infrastructure.UserProfileMapper;
import com.health.ai.security.JwtTokenProvider;
import com.health.ai.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IdentityService {

    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userMapper.findByUsername(request.getUsername()) != null) {
            throw new BusinessException(40001, "Username already exists");
        }
        if (userMapper.findByEmail(request.getEmail()) != null) {
            throw new BusinessException(40001, "Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.insert(user);

        UserProfile profile = new UserProfile();
        profile.setUserId(user.getId());
        profile.setDailyCalorieGoal(2000);
        profile.setCreatedAt(LocalDateTime.now());
        profile.setUpdatedAt(LocalDateTime.now());
        userProfileMapper.insert(profile);

        return buildAuthResponse(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userMapper.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BusinessException(40001, "Invalid username or password");
        }

        return buildAuthResponse(user);
    }

    private AuthResponse buildAuthResponse(User user) {
        AuthResponse response = new AuthResponse();
        response.setToken(jwtTokenProvider.generateToken(user.getId()));
        response.setTokenType("Bearer");
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        return response;
    }
}
