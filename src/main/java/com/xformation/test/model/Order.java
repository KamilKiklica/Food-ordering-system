package com.xformation.test.model;
import com.xformation.test.model.orders.OrderDessert;
import com.xformation.test.model.orders.OrderDish;
import com.xformation.test.model.orders.OrderDrink;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int numberOfOrder;
    private List<OrderDish> listOfOrderedDishes;
    private List<OrderDessert> listOfOrderedDesserts;
    private List<OrderDrink> listOfOrderedDrinks;

    public Order() {
        this.listOfOrderedDishes = new ArrayList<>();
        this.listOfOrderedDesserts= new ArrayList<>();
        this.listOfOrderedDrinks= new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNumberOfOrder() {
        return numberOfOrder;
    }
    public void setNumberOfOrder(int numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }
    public List<OrderDish> getListOfOrderedDishes() {
        return listOfOrderedDishes;
    }
    public List<OrderDessert> getListOfOrderedDesserts() {
        return listOfOrderedDesserts;
    }
    public List<OrderDrink> getListOfOrderedDrinks() {
        return listOfOrderedDrinks;
    }
    public void  addOrderDishToListOfOrderedDishes(OrderDish orderDish){
        listOfOrderedDishes.add(orderDish);}
    public void  addOrderDessertToListOfOrderedDesserts(OrderDessert orderDessert){
        listOfOrderedDesserts.add(orderDessert);}
    public void  addOrderDrinkToListOfOrderedDrinks(OrderDrink orderDrink){
        listOfOrderedDrinks.add(orderDrink);}
}
