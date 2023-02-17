package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (title == null || title.isEmpty()) {
            request.setAttribute("titleError", "Title is required");
            request.setAttribute("title", title);
            request.setAttribute("description", description);
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        Ad ad = new Ad(user.getId(), title, description);
        DaoFactory.getAdsDao().insert(ad);
        Long adId = DaoFactory.getAdsDao().findAds(description);

        int[] categories = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] checkboxNames = {"sedan", "grand", "crossover", "sav", "suv", "compact", "convertible", "foreign", "domestic"};

        for (int i = 0; i < categories.length; i++) {
            if (request.getParameter(checkboxNames[i]) != null) {
                DaoFactory.getCategoriesDao().assign(adId, categories[i]);
            }
        }

        response.sendRedirect("/ads");
    }

}