package com.expensetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponse(
        Long id,
        String description,
        BigDecimal amount,
        LocalDate expenseDate,
        Long categoryId,
        String categoryName) {
}