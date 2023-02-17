package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    List<User> all(String searchTerm);

    User findByUsername(String username);

    User findByUserID(String searchTerm);

    Long insert(User user);

    Long edit(User user);
}
