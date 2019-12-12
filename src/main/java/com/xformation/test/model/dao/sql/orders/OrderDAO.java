package com.xformation.test.model.dao.sql.orders;

import com.xformation.test.model.dao.sql.DataSource;
import com.xformation.test.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO  {

    public List<Order> read() {
        List<Order> list = new ArrayList<>();
        try (ResultSet rs = new DataSource().executeQuery("SELECT * FROM ORDERS;")) {
            while(rs.next()){
                int id = rs.getInt("Id");
                int orderNumber = rs.getInt("Order_Number");
                Order order = new Order();
                order.setId(id);
                order.setNumberOfOrder(orderNumber);
                list.add(order);
            }
        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;    }

    public List<Order> readOrderByOrderNumber(int orderNumber) {
        List<Order> list = new ArrayList<>();
        try (ResultSet rs = new DataSource().executeQuery("SELECT * FROM ORDERS WHERE ORDER_NUMBER="+orderNumber+";")) {
            while(rs.next()){
                int id = rs.getInt("Id");
                int numberOfOrder = rs.getInt("Order_Number");
                Order order = new Order();
                order.setId(id);
                order.setNumberOfOrder(numberOfOrder);
                list.add(order);
            }

        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;    }

    public void createNewOrder(Order order) {

        try (Connection c = new DataSource().getConnection()) {
            String query = " insert into orders (Order_Number)"
                    + " values (?)";

            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setInt(1, order.getNumberOfOrder());
            preparedStmt.execute();

        } catch (SQLException e) {
            System.err.println("SQL exception when creating. ");
            e.printStackTrace();
        }
    }
}
