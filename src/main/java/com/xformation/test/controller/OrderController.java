package com.xformation.test.controller;

import com.xformation.test.controller.Interfaces.OrderControllerInterface;
import com.xformation.test.model.Menu;
import com.xformation.test.model.Order;
import com.xformation.test.model.dao.sql.orders.*;
import com.xformation.test.view.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderController implements OrderControllerInterface {
    private Menu menu;
    private Order order;
    private int maxAmountOfOrders;
    private LunchController lunchController;
    private DrinkController drinkController;



    public OrderController(Menu menu){
        this.order = new Order();
        this.menu = menu;
        this.maxAmountOfOrders=100;
        this.lunchController = new LunchController(menu, order);
        this.drinkController = new DrinkController(menu,order);
    }
    @Override
    public void run() {
        boolean isRunning = true;

        while (isRunning) {

            Display.clearScreen();
            Display.printFileContent("orderMenu");
            int option = Display.askForInt("Choose an option:");

            switch (option) {

                case 1: {
                    this.lunchController.setLunch();
                    break;
                }
                case 2: {
                    this.drinkController.setDrinks();
                    break;
                }
                case 3: {
                    System.out.println("SHOW ACTUAL ORDER");
                    Display.showActualOrder(menu,order);
                    Display.pressButtonToContinue();
                    break;

                }
                case 4: {
                    initializeNewOrder(order);
                    Display.printMessage("Your order have been placed successfully!");
                    Display.printMessage("Number of your order is: "+order.getNumberOfOrder()+".");
                }
                case 0: {
                    isRunning = false;
                    break;
                }
                default: {
                }

            }
        }
    }
    @Override
    public void initializeNewOrder(Order newOrder) {
        try {
        generateRandomOrderNumberBasingOnCurrentAmountOfOrders(newOrder, this.maxAmountOfOrders);
        int orderNumber = newOrder.getNumberOfOrder();
        sendOrder(newOrder, orderNumber);
        } catch (Exception e) {
            System.out.println("The amount of orders at this moment reached maximum size. Please wait for service of other clients.");
        }
    }
    
    private void sendOrder(Order order, int numberOfOrder){
        setNumberOfOrderForAllOrderItems(order, numberOfOrder);
        new OrderDAO().createNewOrder(order);
        order.getListOfOrderedDishes().stream().forEach(orderDish -> new OrderDishDAO().createDishOrder(orderDish));
        order.getListOfOrderedDesserts().stream().forEach(orderDessert -> new OrderDessertDAO().createDessertOrder(orderDessert));
        order.getListOfOrderedDrinks().stream().forEach(orderDrink -> new OrderDrinkDAO().createDrinkOrder(orderDrink));
        order.getListOfOrderedDrinks().stream().forEach(orderDrink -> orderDrink.getListOfAdditives().stream().forEach(orderDrinkAdditive -> new OrderDrinkAdditivesDAO().createDrinkAdditivesOrder(orderDrinkAdditive)));

    }

    private void setNumberOfOrderForAllOrderItems(Order order, int numberOfOrder) {
        order.getListOfOrderedDishes().stream().forEach(orderDish -> orderDish.setOrderNumber(numberOfOrder));
        order.getListOfOrderedDesserts().stream().forEach(orderDessert -> orderDessert.setOrderNumber(numberOfOrder));
        order.getListOfOrderedDrinks().stream().forEach(orderDrink -> orderDrink.setOrderNumber(numberOfOrder));
        order.getListOfOrderedDrinks().stream().forEach(orderDrink -> orderDrink.getListOfAdditives().stream().forEach(orderDrinkAdditive -> orderDrinkAdditive.setDrinkId(orderDrink.getDrinkId())));
        order.getListOfOrderedDrinks().stream().forEach(orderDrink -> orderDrink.getListOfAdditives().stream().forEach(orderDrinkAdditive -> orderDrinkAdditive.setOrderNumber(numberOfOrder)));
    }

    private void generateRandomOrderNumberBasingOnCurrentAmountOfOrders(Order order, int maxAmountOfOrders){
        Random rand = new Random();
        List<Order> ordersList = new OrderDAO().read();
        if (ordersList.size()==maxAmountOfOrders){
            throw new ArrayIndexOutOfBoundsException("Max amount of orders reached");
        } else {
            List<Integer> listOfForbiddenNumbers = new ArrayList<>();
            for(Order orderr: ordersList){
                listOfForbiddenNumbers.add(orderr.getNumberOfOrder());
            }
            int randomOrderNumber= rand.nextInt(maxAmountOfOrders);
            while (listOfForbiddenNumbers.contains(randomOrderNumber)){
                randomOrderNumber= rand.nextInt(maxAmountOfOrders);
            }
            order.setNumberOfOrder(randomOrderNumber);
        }
    }
}
