package com.roshan.expense_tracker.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roshan.expense_tracker.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
