package com.roshan.expense_tracker.expense.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ExpenseRequestDTO {
    @NotNull
    @Min(value = 1, message = "Amount must be positive")
    private Long amount;

    @NotBlank
    @Size(max = 50, message = "Category must be less than 50 characters")
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

    public ExpenseRequestDTO() {
    }



    
}
