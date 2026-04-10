package com.example.ExpenseTracker.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        // public routes (KHÔNG cần login)
        if (uri.startsWith("/login") ||
                uri.startsWith("/register") ||
                uri.startsWith("/api/auth") ||
                uri.startsWith("/css") ||
                uri.startsWith("/js")) {
            return true;
        }

        // check login
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}