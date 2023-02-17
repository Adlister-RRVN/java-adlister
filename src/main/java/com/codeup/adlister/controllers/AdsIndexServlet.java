package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = (String) request.getSession().getAttribute("searchTerm");
        if (searchTerm == null || searchTerm.isEmpty()) {
            request.setAttribute("ads", DaoFactory.getAdsDao().all());
        } else {
            request.setAttribute("found", DaoFactory.getAdsDao().searchAds(searchTerm));
            request.setAttribute("catsearch", DaoFactory.getAdsDao().findAdCategories(searchTerm));
            request.setAttribute("usersearch", DaoFactory.getAdsDao().searchAdsByUser(searchTerm));
        }
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String searchTerm = request.getParameter("search");
        String viewId = request.getParameter("viewId");

        if (viewId != null) {
            User userAd = DaoFactory.getUsersDao().findByUserID(viewId);
            List<Category> categoryAd = DaoFactory.getCategoriesDao().findByCategoryID(viewId);

            request.getSession().setAttribute("viewId", viewId);
            request.getSession().setAttribute("userAd", userAd);
            request.getSession().setAttribute("categoryAd", categoryAd);
            response.sendRedirect("/ads/view");
        } else {
            request.getSession().setAttribute("searchTerm", searchTerm);
            response.sendRedirect("/ads");
        }
    }

}

