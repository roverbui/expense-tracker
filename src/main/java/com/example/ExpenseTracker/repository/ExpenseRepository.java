package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory_Name(String name);

    List<Expense> findByDate(LocalDate date);

    List<Expense> findByDateBetween(LocalDate start, LocalDate end);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE MONTH(e.date) = :month")
    Double getTotalByMonth(@Param("month") int month);

    boolean existsByCategory_Id(Long categoryId);
}
