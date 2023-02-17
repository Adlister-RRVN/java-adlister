package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // If the current user is already logged in (i.e., there is a user attribute in the session), the user is redirected to their profile page.
    if (request.getSession().getAttribute("user") != null) {
      response.sendRedirect("/profile");
      return;
    }

    // The previous URL is stored in the session as a backUrl attribute. This is used to redirect the user back to the previous page after logging in.
    request.getSession().setAttribute("backUrl", request.getHeader("referer"));

    // The user is forwarded to the login.jsp page, which is stored in the WEB-INF folder. This is a JSP page that will display the login form.
    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // The findByUsername method of the UsersDao object is called to retrieve the User object associated with the submitted username.
    User user = DaoFactory.getUsersDao().findByUsername(username);

    // If the user object is null or the submitted password does not match the stored password, the user is redirected back to the login page.
    if (user == null || !Password.check(password, user.getPassword())) {
      // Set an error message in the session if the user does not exist in the database
      if (user == null) {
        request.getSession().setAttribute("errorMessage", "Invalid username.");
      } else {
        request.getSession().setAttribute("errorMessage", "Incorrect password.");
      }
      response.sendRedirect("/login");
      return;
    }

    // If the login is successful, the User object is stored in the session as a user attribute.
    request.getSession().setAttribute("user", user);

    //The backUrl attribute is retrieved from the session and used to redirect the user back to the previous page. If the backUrl is null, the user is redirected to their profile page. The Objects.requireNonNullElse method is used to handle cases where the backUrl is null.
    String backUrl = (String) request.getSession().getAttribute("backUrl");
    response.sendRedirect(Objects.requireNonNullElse(backUrl, "/profile"));
  }

}