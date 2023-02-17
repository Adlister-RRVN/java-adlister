package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.EmailValidator;
import com.codeup.adlister.dao.PasswordValidator;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    // Get user input
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String passwordConfirmation = request.getParameter("confirm_password");

    // Validate input
    boolean hasErrors = username.isEmpty() ||
            email.isEmpty() ||
            password.isEmpty() ||
            !password.equals(passwordConfirmation) ||
            !EmailValidator.isValidEmail(email) ||
            !PasswordValidator.isValidPassword(password);

    // Set error messages based on validation results
    if (email.isEmpty() || !EmailValidator.isValidEmail(email)) {
      request.setAttribute("emailError", "Please enter a valid email address.");
    }

    if (password.isEmpty() || !PasswordValidator.isValidPassword(password)) {
      request.setAttribute("passwordError", "Please enter a valid password with at least 12 characters, including 2 uppercase, 2 lowercase, and 2 special characters.");
    }

    if (hasErrors) {
      // Forward the request to the registration page
      request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
      return;
    }

    // Create and save a new user
    User user = new User(username, email, password);
    DaoFactory.getUsersDao().insert(user);
    response.sendRedirect("/login");
  }
}
