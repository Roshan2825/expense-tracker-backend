package com.roshan.expense_tracker.auth.service;

import com.roshan.expense_tracker.auth.dto.LoginRequestDTO;
import com.roshan.expense_tracker.auth.dto.LoginResponseDTO;
import com.roshan.expense_tracker.auth.dto.RegisterRequestDTO;
import com.roshan.expense_tracker.auth.dto.RegisterResponseDTO;


public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    RegisterResponseDTO createUser(RegisterRequestDTO user);
}
