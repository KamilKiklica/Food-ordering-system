package com.xformation.test.model.dao.sql.food.interfaces;

import com.xformation.test.model.food.Cuisine;

import java.util.List;

public interface CuisineDAOInterface {
    List<Cuisine> read();
}
