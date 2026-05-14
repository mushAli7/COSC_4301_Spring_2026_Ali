package org.example.neonark.service;

import org.example.neonark.dto.AdminUserResponse;
import org.example.neonark.entity.User;
import org.example.neonark.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {

    private final UserRepository userRepository;

    public AdminUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AdminUserResponse> listAdminUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::toAdminUserResponse)
                .toList();
    }

    private AdminUserResponse toAdminUserResponse(User user) {

        String roleName = user.getRoles()
                .stream()
                .findFirst()
                .map(role -> role.getRoleName())
                .orElse("UNASSIGNED");

        return new AdminUserResponse(
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getUsername(),
                roleName
        );
    }
}