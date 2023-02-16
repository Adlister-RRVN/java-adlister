package com.codeup.adlister.dao;

import com.codeup.adlister.controllers.Config;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories {



    private Connection connection = null;

    public MySQLCategoriesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    public List<Category> findByCategoryID(String searchTerm) {
        try {
                String searchTitle = "SELECT * FROM categories WHERE id IN (SELECT category_id FROM ads_categories WHERE ad_id = ?)";
                String searchTermWithWildcards = searchTerm;
                PreparedStatement stmt = connection.prepareStatement(searchTitle);
                stmt.setString(1, searchTermWithWildcards);
                ResultSet rs = stmt.executeQuery();
                return createCategoriesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding categories for ad", e);
        }
    }
    private Category extractCategory(ResultSet rs) throws SQLException {
        return new Category(
                rs.getLong("id"),
                rs.getString("name")
        );
    }

    public List<Category> createCategoriesFromResults(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(extractCategory(rs));
        }
        return categories;
    }


    public Integer assign(Long adId, Integer categoryId){
        try {
        String insertQuery = "INSERT INTO ads_categories(ad_id, category_id) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        stmt.setLong(1, adId);
        stmt.setString(2, String.valueOf(categoryId));
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return 1;
    } catch (SQLException e) {
        throw new RuntimeException("Error setting adID and category.", e);
    }



}

}
