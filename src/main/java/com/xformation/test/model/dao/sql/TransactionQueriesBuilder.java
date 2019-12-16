package com.xformation.test.model.dao.sql;

import com.xformation.test.model.Order;

import java.util.ArrayList;
import java.util.List;

public class TransactionQueriesBuilder {
    private Order order;
    private List<String> queriesList;

    public TransactionQueriesBuilder(Order order) {
        this.order = order;
        this.queriesList = new ArrayList<>();
    }

    public void makeQueriesFromOrder(){
        prepareQueryList(this.order);
    }

    public List<String> getQueriesList() {
        return this.queriesList;
    }

    private void prepareQueryList(Order order) {
        int numberOfOrder = order.getNumberOfOrder();
        addNewOrderQuery(order,numberOfOrder);
        addQueriesForOrderDishes(order, numberOfOrder);
        addQueriesForOrderDesserts(order, numberOfOrder);
        addQueriesForOrderDrinks(order, numberOfOrder);
        addQueriesForOrderDrinkAdditives(order);
    }

    private void addNewOrderQuery(Order order, int numberOfOrder) {
        this.queriesList.add("insert into orders (Order_Number) values ("+numberOfOrder+");");
    }
    private void addQueriesForOrderDishes(Order order, int numberOfOrder) {
        order.getListOfOrderedDishes().
                stream().forEach(orderDish -> this.queriesList
                .add("insert into ordered_dishes (Order_Id, Dish_Id, Amount) " +
                        "values ((SELECT Orders.Id FROM Orders WHERE ORDER_NUMBER="+numberOfOrder+"),"
                        +orderDish.getDishId()+","+orderDish.getAmount()+");"));
    }

    private void addQueriesForOrderDesserts(Order order, int numberOfOrder) {
        order.getListOfOrderedDesserts().
                stream().forEach(orderDessert -> this.queriesList
                .add("insert into ordered_desserts (Order_Id, Dessert_Id, Amount) " +
                        "values ((SELECT Orders.Id FROM Orders WHERE ORDER_NUMBER="+numberOfOrder+"),"
                        +orderDessert.getDessertId()+","+orderDessert.getAmount()+");"));
    }

    private void addQueriesForOrderDrinks(Order order, int numberOfOrder) {
        order.getListOfOrderedDrinks().
                stream().forEach(orderDrink -> this.queriesList
                .add("insert into ordered_drinks (Order_Id, Drink_Id, Amount, Drink_Identifier) " +
                        "values ((SELECT Orders.Id FROM Orders WHERE ORDER_NUMBER="+numberOfOrder+"),"
                        +orderDrink.getDrinkId()+","+orderDrink.getAmount()+","+orderDrink.getDrinkIdentifier()+");"));
    }

    private void addQueriesForOrderDrinkAdditives(Order order) {
        order.getListOfOrderedDrinks().
                stream().forEach(orderDrink -> orderDrink.getListOfAdditives().stream().forEach(orderDrinkAdditive ->
                this.queriesList.add("insert into ordered_drinks_additive (Ordered_Drink_Id, Drink_Additive_Id) " +
                        "values ((SELECT ordered_drinks.Id FROM ordered_drinks WHERE Drink_Identifier="+
                        orderDrink.getDrinkIdentifier()+" AND Order_Id = (SELECT Orders.Id FROM Orders WHERE ORDER_NUMBER="+
                        order.getNumberOfOrder()+"))," +orderDrinkAdditive.getDrinkAdditiveId()+");")));
    }
}
