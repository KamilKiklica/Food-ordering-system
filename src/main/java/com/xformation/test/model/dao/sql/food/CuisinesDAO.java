package com.xformation.test.model.dao.sql.food;

import com.xformation.test.model.dao.sql.DataSource;
import com.xformation.test.model.dao.sql.food.interfaces.CuisineDAOInterface;
import com.xformation.test.model.food.Cuisine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuisinesDAO implements CuisineDAOInterface {
    @Override
    public List<Cuisine> read() {
        List<Cuisine> list = new ArrayList<>();
        try (ResultSet rs = new DataSource().executeQuery("SELECT * FROM CUISINES;")) {
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                Cuisine cuisine = new Cuisine(id, name);
                list.add(cuisine);
            }
        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;    }
}
