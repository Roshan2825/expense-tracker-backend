package com.roshan.expense_tracker.expense.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roshan.expense_tracker.common.ApiResponse;
import com.roshan.expense_tracker.expense.dto.ExpenseRequestDTO;
import com.roshan.expense_tracker.expense.dto.ExpenseResponseDTO;
import com.roshan.expense_tracker.expense.dto.ExpenseUpdateRequestDTO;
import com.roshan.expense_tracker.expense.service.ExpenseService;

import jakarta.validation.Valid;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@RequestMapping("api/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService=expenseService;
    }

    @PostMapping("/my")
    public ResponseEntity<ApiResponse<ExpenseResponseDTO>> createExpense(@Valid @RequestBody ExpenseRequestDTO expenseRequestDTO) {
        ExpenseResponseDTO expenseResponseDTO=expenseService.createExpense(expenseRequestDTO);
        ApiResponse<ExpenseResponseDTO> apiResponse= new ApiResponse<>(true,"Expense created successfully",expenseResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<Page<ExpenseResponseDTO>>> getExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {
        Page<ExpenseResponseDTO> expenseResponseDTOList= expenseService.getMyExpenses(page,size,sortBy);
        ApiResponse<Page<ExpenseResponseDTO>> apiResponse = new ApiResponse<>(true, "Expense fetched successfully", expenseResponseDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<ExpenseResponseDTO>> deleteExpenseById(@PathVariable Long id){
        expenseService.deleteExpenseById(id);
        ApiResponse<ExpenseResponseDTO> apiResponse = new ApiResponse<>(true, "Expense deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponseDTO>> updateExpense(
        @PathVariable Long id, 
        @RequestBody ExpenseUpdateRequestDTO updateDto) {
    
        ExpenseResponseDTO updated = expenseService.updateExpenseById(id, updateDto);
    
        return ResponseEntity.ok(new ApiResponse<>(true, "Expense updated successfully", updated));
}
}
