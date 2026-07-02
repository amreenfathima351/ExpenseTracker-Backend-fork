package com.expensetracker.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.dto.ExpenseResponse;
import com.expensetracker.dto.MonthlySummaryResponse;
import com.expensetracker.entity.Category;
import com.expensetracker.entity.Expense;
import com.expensetracker.exception.ResourceNotFoundException;
import com.expensetracker.repository.CategoryRepository;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ExpenseResponse createExpense(ExpenseRequest request) {
        Category category = getCategoryEntity(request.categoryId());

        Expense expense = Expense.builder()
                .description(request.description().trim())
                .amount(request.amount())
                .expenseDate(request.expenseDate())
                .category(category)
                .build();

        return toResponse(expenseRepository.save(expense));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseResponse> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseResponse getExpenseById(Long id) {
        return toResponse(getExpenseEntity(id));
    }

    @Override
    public ExpenseResponse updateExpense(Long id, ExpenseRequest request) {
        Expense expense = getExpenseEntity(id);
        Category category = getCategoryEntity(request.categoryId());

        expense.setDescription(request.description().trim());
        expense.setAmount(request.amount());
        expense.setExpenseDate(request.expenseDate());
        expense.setCategory(category);

        return toResponse(expenseRepository.save(expense));
    }

    @Override
    public void deleteExpense(Long id) {
        Expense expense = getExpenseEntity(id);
        expenseRepository.delete(expense);
    }

    @Override
    @Transactional(readOnly = true)
    public MonthlySummaryResponse getMonthlySummary(int year, int month) {
        YearMonth requestedMonth = YearMonth.of(year, month);
        LocalDate startDate = requestedMonth.atDay(1);
        LocalDate endDate = requestedMonth.atEndOfMonth();

        List<Expense> expenses = expenseRepository.findAllByExpenseDateBetween(startDate, endDate);
        BigDecimal totalAmount = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new MonthlySummaryResponse(year, month, totalAmount, expenses.size());
    }

    private Expense getExpenseEntity(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
    }

    private Category getCategoryEntity(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
    }

    private ExpenseResponse toResponse(Expense expense) {
        return new ExpenseResponse(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getExpenseDate(),
                expense.getCategory().getId(),
                expense.getCategory().getName());
    }
}