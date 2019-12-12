package com.xformation.test.controller;

import com.xformation.test.controller.Interfaces.LunchControllerInterface;
import com.xformation.test.model.Menu;
import com.xformation.test.model.food.Cuisine;
import com.xformation.test.model.food.MenuItem;
import com.xformation.test.model.orders.OrderDessert;
import com.xformation.test.model.orders.OrderDish;
import com.xformation.test.model.Order;
import com.xformation.test.view.Display;

import java.util.ArrayList;
import java.util.List;

public class LunchController implements LunchControllerInterface {
    private Menu menu;
    private Order order;

    public LunchController(Menu menu, Order order) {
        this.menu = menu;
        this.order = order;
    }
    @Override
    public void setLunch(){
        addDishesToListOfOrderedDishes();
        addDessertToListOfOrderedDesserts();
    }

    private void addAllAvailableDishesIdFromMenuToList(List<Integer> listOfAvailableDishesId) {
        for (Cuisine cuisine: menu.getCuisines()) {
            addAllAvailableItemOrderIdFromMenuToList(listOfAvailableDishesId, cuisine.getListOfDishes());
        }
    }
    private void addAllAvailableItemOrderIdFromMenuToList(List<Integer> listOfAvailableIds, List<MenuItem> listOfMenuItems) {
        for (MenuItem menuItem : listOfMenuItems) {
            listOfAvailableIds.add(menuItem.getId());
        }
    }
    @Override
    public void addDishesToListOfOrderedDishes() {
        int dishId = Display.askForInt("Select ID of dish:");
        List<Integer> listOfAvailableDishesId = new ArrayList<>();
        addAllAvailableDishesIdFromMenuToList(listOfAvailableDishesId);
        while (!listOfAvailableDishesId.contains(dishId)){
            dishId = Display.askForInt("Please input correct ID of dish");
        }
        int amount = Display.askForInt("How many of those?");
        OrderDish orderDish = new OrderDish();
        orderDish.setOrderNumber(order.getNumberOfOrder());
        orderDish.setDishId(dishId);
        orderDish.setAmount(amount);
        order.addOrderDishToListOfOrderedDishes(orderDish);
    }
    @Override
    public void addDessertToListOfOrderedDesserts(){
        int dessertId = Display.askForInt("Select ID of dessert:");
        List<Integer> listOfAvailableDessertId = new ArrayList<>();
        addAllAvailableItemOrderIdFromMenuToList(listOfAvailableDessertId, menu.getListOfDesserts());
        while (!listOfAvailableDessertId.contains(dessertId)){
            dessertId = Display.askForInt("Please input correct id of dessert");
        }
        int amount = Display.askForInt("How many of those desserts do you want?");
        OrderDessert orderDessert = new OrderDessert();
        orderDessert.setOrderNumber(order.getNumberOfOrder());
        orderDessert.setDessertId(dessertId);
        orderDessert.setAmount(amount);
        order.addOrderDessertToListOfOrderedDesserts(orderDessert);
    }
}
