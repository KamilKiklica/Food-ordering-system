package com.xformation.test.model.dao.sql.food;

import com.xformation.test.model.dao.sql.DataSource;
import com.xformation.test.model.food.Dish;
import com.xformation.test.model.food.MenuItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishesDAO {

    public List<MenuItem> read(int cuisineId) {

        List<MenuItem> list = new ArrayList<>();
        try (ResultSet rs = new DataSource().executeQuery("SELECT * FROM DISHES WHERE CUISINE_ID=" + cuisineId + ";")) {
            while(rs.next()){
                int id = rs.getInt("id");
                int cuisine = rs.getInt("Cuisine_Id");
                String name = rs.getString("Name");
                int price = rs.getInt("Price");
                MenuItem dish = new Dish(id, name , price);
                list.add(dish);
            }

        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;    }

}
