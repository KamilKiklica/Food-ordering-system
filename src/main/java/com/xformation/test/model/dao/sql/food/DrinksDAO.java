package com.xformation.test.model.dao.sql.food;

import com.xformation.test.model.dao.sql.DataSource;
import com.xformation.test.model.food.Drink;
import com.xformation.test.model.food.MenuItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrinksDAO{

    public List<MenuItem> read() {

        List<MenuItem> list = new ArrayList<>();
        try (ResultSet rs = new DataSource().executeQuery("SELECT * FROM DRINKS;")) {
            while(rs.next()){
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int price = rs.getInt("Price");
                MenuItem drink = new Drink(id,name,price);
                list.add(drink);
            }

        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;    }


}
