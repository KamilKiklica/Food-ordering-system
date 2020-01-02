package com.xformation.test.model.dao.sql.food.interfaces;

import com.xformation.test.model.food.MenuItem;

import java.util.List;

public interface DishesDAOInterface {
    List<MenuItem> read(int cuisineId);
}
