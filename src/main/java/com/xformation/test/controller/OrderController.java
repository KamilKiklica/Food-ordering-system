package com.xformation.test.controller;

import com.xformation.test.controller.Interfaces.OrderControllerInterface;
import com.xformation.test.model.Menu;
import com.xformation.test.model.Order;
import com.xformation.test.model.dao.sql.TransactionQueriesBuilder;
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



    public OrderController(Menu menu, int maxAmountOfOrders){
        this.order = new Order();
        this.menu = menu;
        this.maxAmountOfOrders = maxAmountOfOrders;
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
        sendOrder(newOrder);
        } catch (Exception e) {
            System.out.println("The amount of orders at this moment reached maximum size. Please wait for service of other clients.");
        }
    }
    
    private void sendOrder(Order order){
        TransactionQueriesBuilder transactionQueriesBuilder = new TransactionQueriesBuilder(order);
        transactionQueriesBuilder.makeQueriesFromOrder();
        List<String> queries = transactionQueriesBuilder.getQueriesList();
        new TransactionDAO().createWholeOrder(queries);
    }

    private void generateRandomOrderNumberBasingOnCurrentAmountOfOrders(Order order, int maxAmountOfOrders){
        Random rand = new Random();
        List<Order> ordersList = new OrderDAO().readListOfOrders();
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
