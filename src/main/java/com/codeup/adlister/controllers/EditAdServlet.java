package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.EditAdServlet", urlPatterns = "/ads/edit")
public class EditAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = (String) request.getSession().getAttribute("viewId");
        request.setAttribute("ads", DaoFactory.getAdsDao().findId(searchTerm));

        request.getRequestDispatcher("/WEB-INF/ads/edit.jsp").forward(request, response);
    }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title");
            String description = request.getParameter("password");


            DaoFactory.getAdsDao().update(title,description,id);
            response.sendRedirect("/ads");

//            if (inputHasErrors) {
//                response.sendRedirect("/profile");
//                return;
//            }

//            User user = (User) request.getSession().getAttribute("user");

            // COMPLETED
//            if (password.isEmpty()) {
//                User updated = new User(user.getId(), username, email, user.getPassword());
//                DaoFactory.getUsersDao().edit(updated);
//                request.getSession().setAttribute("user", updated);
//                response.sendRedirect("/login");
//                return;
//            }

//            User updated = new User(user.getId(), username, email, Password.hash(password));
//            DaoFactory.getUsersDao().edit(updated);
//            request.getSession().setAttribute("user", updated);
//            response.sendRedirect("/login");
        }
}

