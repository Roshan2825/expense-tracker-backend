package com.roshan.expense_tracker.expense.dto;

import java.time.LocalDateTime;

public class ExpenseResponseDTO {
    private Long id;
    private Long amount;
    private String category;
    private LocalDateTime createdAt; 
    private LocalDateTime updatedAt; 
    private Long userId;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public ExpenseResponseDTO() {
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public ExpenseResponseDTO(Long id, Long amount, String category, LocalDateTime createdAt, LocalDateTime updatedAt,
            Long userId) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    
    
}
