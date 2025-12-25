package com.roshan.expense_tracker.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

    @NotBlank(message="Name is mandatory")
    private String name;

    @NotBlank(message="Email is mandatory")
    @Email(message="Email should be valid")
    private String email;

    @NotBlank(message="Password is mandatory")
    @Size(min=6, message="Password should be at least 6 characters")
    private String password;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserRequestDTO() {
    }
    
    
}
