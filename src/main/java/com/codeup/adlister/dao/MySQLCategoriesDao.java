package com.codeup.adlister.dao;

import com.codeup.adlister.controllers.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

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
