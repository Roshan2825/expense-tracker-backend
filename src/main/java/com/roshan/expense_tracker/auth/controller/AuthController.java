package com.roshan.expense_tracker.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roshan.expense_tracker.auth.dto.LoginRequestDTO;
import com.roshan.expense_tracker.auth.dto.LoginResponseDTO;
import com.roshan.expense_tracker.auth.dto.RegisterRequestDTO;
import com.roshan.expense_tracker.auth.dto.RegisterResponseDTO;
import com.roshan.expense_tracker.auth.service.AuthService;
import com.roshan.expense_tracker.common.ApiResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
     
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponseDTO>> createUser(@Valid @RequestBody RegisterRequestDTO user) {
        RegisterResponseDTO userResponseDTO = authService.createUser(user);
        ApiResponse<RegisterResponseDTO> apiResponse = new ApiResponse<>(
            true,"User created successfully",userResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO loginResponseDTO = authService.login(request);
        ApiResponse<LoginResponseDTO> apiResponse = new ApiResponse<>(
            true, "Login successful", loginResponseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    
}
