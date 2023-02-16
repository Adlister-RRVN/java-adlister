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
        if (request.getSession().getAttribute("searchTerm") == null || request.getSession().getAttribute("searchTerm").equals("")){
            request.setAttribute("ads", DaoFactory.getAdsDao().all());
            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
        }
        String searchTerm = (String) request.getSession().getAttribute("searchTerm");
        request.setAttribute("found", DaoFactory.getAdsDao().searchAds(searchTerm));
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchTerm = request.getParameter("search");
        String loadall = request.getParameter("loadall");
        String viewId = request.getParameter("viewId");
        User userAd = DaoFactory.getUsersDao().findByUserID(viewId);
        List<Category> categoryAd = DaoFactory.getCategoriesDao().findByCategoryID(viewId);

        request.getSession().setAttribute("userAd", userAd);
        request.getSession().setAttribute("categoryAd", categoryAd);

        if (viewId != null){
            request.getSession().setAttribute("viewId", viewId);
            response.sendRedirect("/ads/view");
        }
        else {

            if (loadall != null) {
                request.getSession().setAttribute("searchTerm", null);
            }
            request.getSession().setAttribute("searchTerm", searchTerm);

            response.sendRedirect("/ads");
        }
    }
}

