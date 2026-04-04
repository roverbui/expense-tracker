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

    //Get expense by id
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
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

    //Search by date range
    @GetMapping("/date-range")
    public List<Expense> getByDateRange(
            @RequestParam String start,
            @RequestParam String end) {

        return expenseRepository.findByDateBetween(
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }

    //Update expense
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense updatedExpense) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setAmount(updatedExpense.getAmount());
        expense.setDescription(updatedExpense.getDescription());
        expense.setDate(updatedExpense.getDate());
        expense.setCategory(updatedExpense.getCategory());

        return expenseRepository.save(expense);
    }

    //Delete expense by id
    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
        return "Expense deleted successfully";
    }

    //Delete all expenses
    @DeleteMapping("/all")
    public String deleteAllExpenses() {
        expenseRepository.deleteAll();
        return "All expenses deleted successfully";
    }

    //Monthly feature
    @GetMapping("/monthly-total")
    public Double getMonthlyTotal(@RequestParam int month) {
        return expenseRepository.getTotalByMonth(month);
    }
    
}
