package com.roshan.expense_tracker.auth.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.roshan.expense_tracker.auth.dto.LoginRequestDTO;
import com.roshan.expense_tracker.auth.dto.LoginResponseDTO;
import com.roshan.expense_tracker.auth.dto.RegisterRequestDTO;
import com.roshan.expense_tracker.auth.dto.RegisterResponseDTO;
import com.roshan.expense_tracker.auth.service.AuthService;
import com.roshan.expense_tracker.exception.UserNotFoundException;
import com.roshan.expense_tracker.security.jwt.JwtUtil;
import com.roshan.expense_tracker.user.entity.User;
import com.roshan.expense_tracker.user.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
            UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequestDTO.getEmail(),
                                loginRequestDTO.getPassword()));
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        String token = jwtUtil.generateToken(user);

        return new LoginResponseDTO(token);
    }

    @Override
    public RegisterResponseDTO createUser(RegisterRequestDTO user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));// encoded pass
        User savedUser = userRepository.save(newUser);

        return mapToResponseDTO(savedUser);
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
