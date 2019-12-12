package com.xformation.test.model.dao.sql.orders;

import com.xformation.test.model.dao.sql.DataSource;
import com.xformation.test.model.orders.OrderDrinkAdditive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDrinkAdditivesDAO  {

    public List<OrderDrinkAdditive> read() {

        List<OrderDrinkAdditive> list = new ArrayList<>();
        try (ResultSet rs = new DataSource().executeQuery("SELECT * FROM ORDERED_DRINKS_ADDITIVE;")) {
            while(rs.next()){
                int id = rs.getInt("Id");
                int orderNumber = rs.getInt("Order_Number");
                int drinkId = rs.getInt("Drink_Id");
                int drinkAdditiveId = rs.getInt("Drink_Additive_Id");

                OrderDrinkAdditive additive = new OrderDrinkAdditive(id,orderNumber,drinkAdditiveId,drinkId);
                list.add(additive);
            }

        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;    }

    public void createDrinkAdditivesOrder(OrderDrinkAdditive order) {
        try (Connection c = new DataSource().getConnection()) {
            String query = " insert into ordered_drinks_additive (Order_Number, Drink_Id, Drink_Additive_Id)"
                    + " values (?,?,?)";

            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setInt(1, order.getOrderNumber());
            preparedStmt.setInt(2, order.getDrinkId());
            preparedStmt.setInt(3, order.getDrinkAdditiveId());
            preparedStmt.execute();

        } catch (SQLException e) {
            System.err.println("SQL exception when creating. ");
            e.printStackTrace();
        }
    }
}
