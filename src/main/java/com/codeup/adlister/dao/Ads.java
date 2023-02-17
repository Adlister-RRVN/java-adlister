package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    List<Ad> searchAds(String searchTerm);

    List<Ad> findAdCategories(String searchTerm);

    Long findAds(String searchTerm);

    void delete(long id);

    void update(String title, String description, long id);


    List<Ad> findId(String searchTerm);

    List<Ad> searchAdsByUser(String searchTerm);

}