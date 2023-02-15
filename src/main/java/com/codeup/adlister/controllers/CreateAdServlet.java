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
        String sedan = request.getParameter("sedan");
        String grand = request.getParameter("grand");
        String crossover = request.getParameter("crossover");
        String sav = request.getParameter("sav");
        String suv = request.getParameter("suv");
        String compact = request.getParameter("compact");
        String convertible = request.getParameter("convertible");
        String foreign = request.getParameter("foreign");
        String domestic = request.getParameter("domestic");

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
        if (sedan != null){
            DaoFactory.getCategoriesDao().assign(adId, 1);
        }
        if (grand != null){
            DaoFactory.getCategoriesDao().assign(adId, 2);
        }
        if (crossover != null){
            DaoFactory.getCategoriesDao().assign(adId, 3);
        }
        if (sav != null){
            DaoFactory.getCategoriesDao().assign(adId, 4);
        }
        if (suv != null){
            DaoFactory.getCategoriesDao().assign(adId, 5);
        }
        if (compact != null){
            DaoFactory.getCategoriesDao().assign(adId, 6);
        }
        if (convertible != null){
            DaoFactory.getCategoriesDao().assign(adId, 7);
        }
        if (foreign != null){
            DaoFactory.getCategoriesDao().assign(adId, 8);
        }
        if (domestic != null){
            DaoFactory.getCategoriesDao().assign(adId, 9);
        }
        response.sendRedirect("/ads");
    }
}