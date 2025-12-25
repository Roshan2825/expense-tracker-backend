package com.roshan.expense_tracker.expense.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roshan.expense_tracker.expense.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long>{
    Page<Expense> findByUserId(Long userId,Pageable pageable);
}
