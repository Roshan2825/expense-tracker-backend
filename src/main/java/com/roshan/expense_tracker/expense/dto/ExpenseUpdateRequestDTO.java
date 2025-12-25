package com.roshan.expense_tracker.expense.dto;

import jakarta.validation.constraints.Min;

public class ExpenseUpdateRequestDTO {
    @Min(value = 1, message = "Amount must be positive")
    private Long amount;
    private String category;

    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public ExpenseUpdateRequestDTO() {
    }
    
    
}
