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

//  GET ALL CATEGORIES
    @GetMapping
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

//  GET A CATEGORY BY ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

//  CREATE A CATEGORY
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }


// UPDATE A CATEGORY BY ID
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {

    Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));

    category.setName(updatedCategory.getName());

    return categoryRepository.save(category);
    }


//    DELETE A CATEGORY BY ID
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {

        if (expenseRepository.existsByCategory_Id(id)) {
            return "Cannot delete: Category is used by expenses";
        }

        categoryRepository.deleteById(id);
        return "Category deleted successfully";
    }
}
