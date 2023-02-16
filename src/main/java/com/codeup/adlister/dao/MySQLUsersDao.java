package com.codeup.adlister.dao;

import com.codeup.adlister.controllers.Config;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUsersDao implements Users {
  private Connection connection;

  public MySQLUsersDao(Config config) {
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


  // NOT IN USE
  public List<User> all(String searchTerm) {
    PreparedStatement stmt = null;
    try {
      stmt = connection.prepareStatement("SELECT * FROM users WHERE id = (SELECT user_id FROM ads WHERE id = ?)");
      System.out.println(searchTerm);
      stmt.setString(1, searchTerm);
      ResultSet rs = stmt.executeQuery();
      return createUsersFromResults(rs);
    } catch (SQLException e) {
      throw new RuntimeException("Error retrieving all users.", e);
    }
  }

  //    I DON'T THINK IN USE
  private List<User> createUsersFromResults(ResultSet rs) throws SQLException {
    List<User> users = new ArrayList<>();
    while (rs.next()) {
      users.add(extractUser(rs));
    }
    return users;
  }

  @Override
  public User findByUsername(String username) {
    String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
    try {
      PreparedStatement stmt = connection.prepareStatement(query);
      stmt.setString(1, username);
      return extractUser(stmt.executeQuery());
    } catch (SQLException e) {
      throw new RuntimeException("Error finding a user by username", e);
    }
  }


  public User findByUserID(String searchTerm) {
    String query = "SELECT * FROM users WHERE id = ? LIMIT 1";
    try {
      PreparedStatement stmt = connection.prepareStatement(query);
      stmt.setString(1, searchTerm);
      return extractUser(stmt.executeQuery());
    } catch (SQLException e) {
      throw new RuntimeException("Error finding a user by username", e);
    }
  }

  @Override
  public Long insert(User user) {
    String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    try {
      PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getEmail());
      stmt.setString(3, user.getPassword());
      stmt.executeUpdate();
      ResultSet rs = stmt.getGeneratedKeys();
      rs.next();
      return rs.getLong(1);
    } catch (SQLException e) {
      throw new RuntimeException("Error creating new user", e);
    }
  }


  @Override
  public Long edit(User user) {
    String query = "UPDATE users SET username= ?, email= ?, password = ? WHERE id = ?";
    try {
      PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getEmail());
      stmt.setString(3, user.getPassword());
      stmt.setString(4, String.valueOf(user.getId()));
      stmt.executeUpdate();
      ResultSet rs = stmt.getGeneratedKeys();
      rs.next();
      return user.getId();
    } catch (SQLException e) {
      throw new RuntimeException("Error editing user", e);
    }
  }
//    UPDATE users
//    SET username = 'Samuel', email = 'Clemens@gmail.com', password = 'password'
//    WHERE id = 4;

  private User extractUser(ResultSet rs) throws SQLException {
    if (!rs.next()) {
      return null;
    }
    return new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password")
    );
  }
}