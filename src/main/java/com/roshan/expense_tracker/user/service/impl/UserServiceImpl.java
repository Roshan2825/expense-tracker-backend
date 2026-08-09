package com.roshan.expense_tracker.user.service.impl;

import org.springframework.stereotype.Service;

import com.roshan.expense_tracker.auth.dto.RegisterResponseDTO;
import com.roshan.expense_tracker.exception.UserNotFoundException;

import com.roshan.expense_tracker.security.util.SecurityUtil;
import com.roshan.expense_tracker.user.entity.User;
import com.roshan.expense_tracker.user.repository.UserRepository;
import com.roshan.expense_tracker.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterResponseDTO getUser() {
    Long userId = SecurityUtil.getCurrentUserId();
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
        
    return mapToResponseDTO(user);
    }

    private RegisterResponseDTO mapToResponseDTO(User user) {
        RegisterResponseDTO userResponseDTO = new RegisterResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getCreatedAt(), 
            user.getUpdatedAt());

        return userResponseDTO;   
    }
}
    

