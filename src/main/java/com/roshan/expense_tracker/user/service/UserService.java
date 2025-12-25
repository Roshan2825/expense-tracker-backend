package com.roshan.expense_tracker.user.service;


import com.roshan.expense_tracker.user.dto.UserRequestDTO;
import com.roshan.expense_tracker.user.dto.UserResponseDTO;


public interface UserService {
    UserResponseDTO createUser(UserRequestDTO user);
    UserResponseDTO getUser();
}
