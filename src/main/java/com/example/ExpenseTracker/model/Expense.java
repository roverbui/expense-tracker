package com.example.ExpenseTracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String description;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Expense() {

    }
}
