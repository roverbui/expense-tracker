package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory_Name(String category);

    List<Expense> findByDate(LocalDate date);
}
