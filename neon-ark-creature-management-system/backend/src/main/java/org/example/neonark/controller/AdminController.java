package org.example.neonark.controller;

import org.example.neonark.dto.AdminUserResponse;
import org.example.neonark.service.AdminUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Handles admin visibility routes for system users.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminUserService adminUserService;

    public AdminController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserResponse>> listAllUsers() {
        return ResponseEntity.ok(adminUserService.listAdminUsers());
    }
}