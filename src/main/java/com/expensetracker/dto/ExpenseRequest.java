package com.expensetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ExpenseRequest(
        @NotBlank(message = "Expense description is required") @Size(max = 255, message = "Expense description must be at most 255 characters") String description,

        @NotNull(message = "Expense amount is required") @DecimalMin(value = "0.01", message = "Expense amount must be greater than zero") BigDecimal amount,

        @NotNull(message = "Expense date is required") LocalDate expenseDate,

        @NotNull(message = "Category id is required") Long categoryId) {
}