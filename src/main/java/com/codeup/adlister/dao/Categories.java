package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Categories {
    // assigns AdID's to Category ID's in the ads_categories table
    Integer assign(Long adID, Integer categoryID);
}
