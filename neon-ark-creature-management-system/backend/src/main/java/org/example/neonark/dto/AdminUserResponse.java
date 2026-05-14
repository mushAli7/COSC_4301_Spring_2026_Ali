package org.example.neonark.dto;

public record AdminUserResponse(
        Long userId,
        String fullName,
        String email,
        String phone,
        String username,
        String role
) {
}