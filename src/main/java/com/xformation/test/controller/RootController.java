package com.xformation.test.controller;

import com.xformation.test.controller.Interfaces.RootControllerInterface;
import com.xformation.test.model.Menu;
import com.xformation.test.model.dao.sql.food.CuisinesDAO;
import com.xformation.test.model.dao.sql.food.DessertsDAO;
import com.xformation.test.model.dao.sql.food.DrinkAdditivesDAO;
import com.xformation.test.model.dao.sql.food.DrinksDAO;
import com.xformation.test.model.dao.sql.orders.OrderDAO;
import com.xformation.test.model.Order;
import com.xformation.test.view.Display;

import java.util.List;

public class RootController implements RootControllerInterface {
    private Menu menu = new Menu();


    public RootController(){}
    @Override
    public void run() {

        boolean isRunning = true;
        while (isRunning) {
            Display.clearScreen();
            printMenu();

            int option = Display.askForInt("Input option number:");

            switch (option) {

                case 1: {
                    Display.clearScreen();
                    OrderController orderController = new OrderController(menu);
                    orderController.run();
                    Display.pressButtonToContinue();
                    break;
                }
                case 2: {
                    showPendingOrders();
                    Display.pressButtonToContinue();
                    break;
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
    public void showPendingOrders(){
        List<Order> list = new OrderDAO().read();
        Display.showPendingOrders(list);
    }
    @Override
    public void printMenu() {
        readMenuFromDatabase();
        Display.printFileContent("menuASCII");
        Display.printMenu(this.menu);
        Display.printFileContent("startingMenu");
    }
    @Override
    public void readMenuFromDatabase(){
        menu.setListOfDesserts(new DessertsDAO().read());
        menu.setListOfDrinks(new DrinksDAO().read());
        menu.setListOfDrinkAdditives(new DrinkAdditivesDAO().read());
        menu.setCuisinesAndDishes(new CuisinesDAO().read());
    }
}
