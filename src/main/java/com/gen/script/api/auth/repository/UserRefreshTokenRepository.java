package com.gen.script.api.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gen.script.api.auth.entity.UserRefreshToken;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    UserRefreshToken findByUserId(String userId);
    UserRefreshToken findByUserIdAndRefreshToken(String userId, String refreshToken);
}