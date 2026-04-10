package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // ---------- DASHBOARD ----------
    @GetMapping("/")
    public String home(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "index";
    }

    // ---------- EXPENSES ----------
    @GetMapping("/expenses")
    public String expenses(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "expenses";
    }

    // ---------- ADD EXPENSE ----------
    @GetMapping("/add_expense")
    public String addExpense(HttpSession session) {

        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        return "add_expense";
    }

    // ---------- PROFILE ----------
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "profile";
    }

    // ---------- ADMIN ----------
    @GetMapping("/admin")
    public String admin(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        // check role
        if (!"ADMIN".equals(user.getRole())) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        return "admin";
    }

    // ---------- LOGIN ----------
    @GetMapping("/login")
    public String loginPage(HttpSession session) {

        // nếu đã login rồi thì khỏi vào login nữa
        if (session.getAttribute("loggedUser") != null) {
            return "redirect:/";
        }

        return "login";
    }

    // ---------- REGISTER ----------
    @GetMapping("/register")
    public String registerPage(HttpSession session) {

        if (session.getAttribute("loggedUser") != null) {
            return "redirect:/";
        }

        return "register";
    }
}