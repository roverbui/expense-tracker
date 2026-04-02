package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    //Create expense
    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    //Basic REST API

    //Get all
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    //Filter by category
    @GetMapping("/category")
    public List<Expense> getByCategory(@RequestParam String name) {
        return expenseRepository.findByCategory_Name(name);
    }

    //Filter by date
    @GetMapping("/date")
    public List<Expense> getByDate(@RequestParam String date) {
        return expenseRepository.findByDate(LocalDate.parse(date));
    }
}
