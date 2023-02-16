package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Categories {
    List<Category> findByCategoryID(String searchTerm);
    List<Category> createCategoriesFromResults(ResultSet rs) throws SQLException;

    // assigns AdID's to Category ID's in the ads_categories table
    Integer assign(Long adID, Integer categoryID);
}

