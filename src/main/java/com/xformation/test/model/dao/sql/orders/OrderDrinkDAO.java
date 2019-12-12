package com.xformation.test.model.dao.sql.orders;

import com.xformation.test.model.dao.sql.DataSource;
import com.xformation.test.model.orders.OrderDrink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDrinkDAO {

    public List<OrderDrink> read() {

        List<OrderDrink> list = new ArrayList<>();
        try (ResultSet rs = new DataSource().executeQuery("SELECT * FROM ORDERED_DRINKS;")) {
            while(rs.next()){
                int id = rs.getInt("Id");
                int orderNumber = rs.getInt("Order_Number");
                int drinkId = rs.getInt("Drink_Id");
                int amount = rs.getInt("Amount");
                OrderDrink drink = new OrderDrink(id,orderNumber,drinkId,amount);
                list.add(drink);
            }

        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;    }

    public void createDrinkOrder(OrderDrink order) {

        try (Connection c = new DataSource().getConnection()) {
            String query = " insert into ordered_drinks (Order_Number, Drink_Id, Amount)"
                    + " values (?,?,?)";

            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setInt(1, order.getOrderNumber());
            preparedStmt.setInt(2, order.getDrinkId());
            preparedStmt.setInt(3, order.getAmount());
            preparedStmt.execute();

        } catch (SQLException e) {
            System.err.println("SQL exception when creating. ");
            e.printStackTrace();
        }
    }
}
