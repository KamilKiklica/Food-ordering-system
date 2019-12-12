package com.xformation.test.model.dao.sql.orders;

import com.xformation.test.model.dao.sql.DataSource;
import com.xformation.test.model.orders.OrderDessert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDessertDAO{


    public List<OrderDessert> read() {

        List<OrderDessert> list = new ArrayList<>();
        try (ResultSet rs = new DataSource().executeQuery("SELECT * FROM ORDERED_DESSERTS;")) {
            while(rs.next()){
                int id = rs.getInt("Id");
                int orderNumber = rs.getInt("Order_Number");
                int dessertId = rs.getInt("Dessert_Id");
                int amount = rs.getInt("Amount");
                OrderDessert dessert = new OrderDessert(id,orderNumber,dessertId,amount);
                dessert.setId(id);
                dessert.setOrderNumber(orderNumber);
                dessert.setDessertId(dessertId);
                dessert.setAmount(amount);
                list.add(dessert);
            }

        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;    }

    public void createDessertOrder(OrderDessert order) {

        try (Connection c = new DataSource().getConnection()) {
            String query = " insert into ordered_desserts (Order_Number, Dessert_Id, Amount)"
                    + " values (?,?,?)";

            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setInt(1, order.getOrderNumber());
            preparedStmt.setInt(2, order.getDessertId());
            preparedStmt.setInt(3, order.getAmount());
            preparedStmt.execute();

        } catch (SQLException e) {
            System.err.println("SQL exception when creating. ");
            e.printStackTrace();
        }
    }
}
