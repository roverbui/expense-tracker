package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.ExpenseTrackerApplication;
import com.example.ExpenseTracker.model.Category;
import com.example.ExpenseTracker.repository.CategoryRepository;
import com.example.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {

        if (expenseRepository.existsByCategory_Id(id)) {
            return "Cannot delete: Category is used by expenses";
        }

        categoryRepository.deleteById(id);
        return "Category deleted successfully";
    }
}
