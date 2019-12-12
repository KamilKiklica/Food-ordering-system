package com.xformation.test.model.dao.sql.orders;

import com.xformation.test.model.dao.sql.DataSource;
import com.xformation.test.model.orders.OrderDish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDishDAO {

    public List<OrderDish> read() {
        List<OrderDish> list = new ArrayList<>();
        try (ResultSet rs = new DataSource().executeQuery("SELECT * FROM ORDERED_DISHES;")) {
            while(rs.next()){
                int id = rs.getInt("Id");
                int orderNumber = rs.getInt("Order_Number");
                int dishId = rs.getInt("Dish_Id");
                int amount = rs.getInt("Amount");
                OrderDish dish = new OrderDish(id,orderNumber,dishId,amount);
                list.add(dish);
            }
        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;    }

    public void createDishOrder(OrderDish order) {

        try (Connection c = new DataSource().getConnection()) {
            String query = " insert into ordered_dishes (Order_Number, Dish_Id, Amount)"
                    + " values (?,?,?)";
            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setInt(1, order.getOrderNumber());
            preparedStmt.setInt(2, order.getDishId());
            preparedStmt.setInt(3, order.getAmount());
            preparedStmt.execute();
        } catch (SQLException e) {
            System.err.println("SQL exception when creating. ");
            e.printStackTrace();
        }
    }
}
