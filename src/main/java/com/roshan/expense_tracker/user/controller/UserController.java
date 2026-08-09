package com.roshan.expense_tracker.user.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roshan.expense_tracker.auth.dto.RegisterResponseDTO;
import com.roshan.expense_tracker.common.ApiResponse;
import com.roshan.expense_tracker.user.service.UserService;


@RestController
public class UserController {

    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users/me")
    public ResponseEntity<ApiResponse<RegisterResponseDTO>> getUser() {
        RegisterResponseDTO userResponseDTO = userService.getUser();
        ApiResponse<RegisterResponseDTO> apiResponse = new ApiResponse<>(
            true,"User fetched successfully",userResponseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
