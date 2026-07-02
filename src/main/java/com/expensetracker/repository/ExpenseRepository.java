package com.expensetracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Override
    @EntityGraph(attributePaths = "category")
    List<Expense> findAll();

    @Override
    @EntityGraph(attributePaths = "category")
    Optional<Expense> findById(Long id);

    @EntityGraph(attributePaths = "category")
    List<Expense> findAllByExpenseDateBetween(LocalDate startDate, LocalDate endDate);

    boolean existsByCategoryId(Long categoryId);
}