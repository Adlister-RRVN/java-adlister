package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("searchTerm") == null || request.getSession().getAttribute("searchTerm").equals("")){
            request.setAttribute("ads", DaoFactory.getAdsDao().all());
            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
        }
        String searchTerm = (String) request.getSession().getAttribute("searchTerm");
        request.setAttribute("found", DaoFactory.getAdsDao().searchAds(searchTerm));
//        request.setAttribute("found", DaoFactory.getAdsDao().searchCategories(searchTerm));
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchTerm = request.getParameter("search");
        String loadall = request.getParameter("loadall");

        if(loadall != null){
            request.getSession().setAttribute("searchTerm", null);
        }


        request.getSession().setAttribute("searchTerm", searchTerm);
        response.sendRedirect("/ads");
    }
}

