package com.roshan.expense_tracker.expense.service;


import org.springframework.data.domain.Page;

import com.roshan.expense_tracker.expense.dto.ExpenseRequestDTO;
import com.roshan.expense_tracker.expense.dto.ExpenseResponseDTO;
import com.roshan.expense_tracker.expense.dto.ExpenseUpdateRequestDTO;


public interface ExpenseService {
    ExpenseResponseDTO createExpense(ExpenseRequestDTO expenseRequestDTO);

    Page<ExpenseResponseDTO> getMyExpenses(int page,int size,String sortBy);

    void deleteExpenseById(Long id);

    ExpenseResponseDTO updateExpenseById(Long id,ExpenseUpdateRequestDTO expenseUpdateRequestDTO);
}
