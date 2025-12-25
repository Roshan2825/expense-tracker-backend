package com.roshan.expense_tracker.user.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roshan.expense_tracker.common.ApiResponse;
import com.roshan.expense_tracker.user.dto.UserRequestDTO;
import com.roshan.expense_tracker.user.dto.UserResponseDTO;
import com.roshan.expense_tracker.user.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@Valid @RequestBody UserRequestDTO user) {
        UserResponseDTO userResponseDTO = userService.createUser(user);
        ApiResponse<UserResponseDTO> apiResponse = new ApiResponse<>(
            true,"User created successfully",userResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @GetMapping("/api/users/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUser() {
        UserResponseDTO userResponseDTO = userService.getUser();
        ApiResponse<UserResponseDTO> apiResponse = new ApiResponse<>(
            true,"User fetched successfully",userResponseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
