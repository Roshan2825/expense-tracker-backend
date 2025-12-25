package com.roshan.expense_tracker.user.service.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.roshan.expense_tracker.security.principal.UserPrincipal;
import com.roshan.expense_tracker.user.entity.User;
import com.roshan.expense_tracker.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username).orElseThrow(
            () -> new UsernameNotFoundException("User not found with email:" + username));
        return new UserPrincipal(
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            List.of() //roles willl be added later
        );
    }

}
