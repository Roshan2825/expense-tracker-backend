package com.roshan.expense_tracker.expense.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.roshan.expense_tracker.expense.dto.ExpenseRequestDTO;
import com.roshan.expense_tracker.expense.dto.ExpenseResponseDTO;
import com.roshan.expense_tracker.expense.dto.ExpenseUpdateRequestDTO;
import com.roshan.expense_tracker.expense.entity.Expense;
import com.roshan.expense_tracker.expense.repository.ExpenseRepository;
import com.roshan.expense_tracker.expense.service.ExpenseService;
import com.roshan.expense_tracker.security.util.SecurityUtil;
import com.roshan.expense_tracker.user.entity.User;
import com.roshan.expense_tracker.user.repository.UserRepository;
import com.roshan.expense_tracker.exception.ExpenseNotFoundException;
import com.roshan.expense_tracker.exception.UserNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExpenseResponseDTO createExpense(ExpenseRequestDTO expenseRequestDTO) {
        Long userId = SecurityUtil.getCurrentUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("UserID not found: " + userId));
        Expense expense = new Expense();
        expense.setAmount(expenseRequestDTO.getAmount());
        expense.setCategory(expenseRequestDTO.getCategory());
        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);
        return mapToDTO(savedExpense);

    }

    @Override
    public Page<ExpenseResponseDTO> getMyExpenses(int page, int size, String sortBy) {
        Long userId = SecurityUtil.getCurrentUserId();
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<Expense> expenseList = expenseRepository.findByUserId(userId, pageable);
        Page<ExpenseResponseDTO> responseList = expenseList.map(expense -> new ExpenseResponseDTO(
                expense.getId(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getCreatedAt(),
                expense.getUpdatedAt(),
                expense.getUser().getId()));
        return responseList;
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFoundException("Expense not found for id:" + id));
        if (validateOwnership(expense)) {
            expenseRepository.delete(expense);
        } else {
            throw new AccessDeniedException("Access Denied");
        }

    }

    private boolean validateOwnership(Expense expense) {

        return Objects.equals(expense.getUser().getId(), SecurityUtil.getCurrentUserId());
    }

    @Override
    public ExpenseResponseDTO updateExpenseById(Long id, ExpenseUpdateRequestDTO expenseUpdateRequestDTO) {
        Expense existingExpense = expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFoundException("Expense not found for id:" + id));
        if (!validateOwnership(existingExpense)) {
            throw new AccessDeniedException("Access Denied");
        }
        if (expenseUpdateRequestDTO.getAmount() != null) {
            existingExpense.setAmount(expenseUpdateRequestDTO.getAmount());
        }
        if (expenseUpdateRequestDTO.getCategory() != null && !expenseUpdateRequestDTO.getCategory().trim().isEmpty()) {
            existingExpense.setCategory(expenseUpdateRequestDTO.getCategory());
        }
        Expense savedExpense = expenseRepository.save(existingExpense);
        return mapToDTO(savedExpense);
    }

    private ExpenseResponseDTO mapToDTO(Expense savedExpense) {
        ExpenseResponseDTO expenseResponseDTO = new ExpenseResponseDTO(
                savedExpense.getId(),
                savedExpense.getAmount(),
                savedExpense.getCategory(),
                savedExpense.getCreatedAt(),
                savedExpense.getUpdatedAt(),
                savedExpense.getUser().getId());
        return expenseResponseDTO;
    }
}
