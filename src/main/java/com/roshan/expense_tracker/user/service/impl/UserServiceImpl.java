package com.roshan.expense_tracker.user.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.roshan.expense_tracker.exception.UserNotFoundException;

import com.roshan.expense_tracker.security.util.SecurityUtil;
import com.roshan.expense_tracker.user.dto.UserRequestDTO;
import com.roshan.expense_tracker.user.dto.UserResponseDTO;
import com.roshan.expense_tracker.user.entity.User;
import com.roshan.expense_tracker.user.repository.UserRepository;
import com.roshan.expense_tracker.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));//encoded pass
        User savedUser = userRepository.save(newUser);

        return mapToResponseDTO(savedUser);
    }
    @Override
    public UserResponseDTO getUser() {
    Long userId = SecurityUtil.getCurrentUserId();
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
        
    return mapToResponseDTO(user);
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getCreatedAt(), 
            user.getUpdatedAt());

        return userResponseDTO;   
    }
}
    

