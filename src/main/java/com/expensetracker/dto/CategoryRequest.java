package com.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "Category name is required") @Size(max = 100, message = "Category name must be at most 100 characters") String name,

        @Size(max = 255, message = "Category description must be at most 255 characters") String description) {
}