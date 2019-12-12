package com.xformation.test.controller;

import com.xformation.test.controller.Interfaces.DrinkControllerInterface;
import com.xformation.test.model.Menu;
import com.xformation.test.model.Order;
import com.xformation.test.model.food.MenuItem;
import com.xformation.test.model.orders.*;
import com.xformation.test.view.Display;

import java.util.ArrayList;
import java.util.List;

public class DrinkController implements DrinkControllerInterface {
    private Menu menu;
    private Order order;

    public DrinkController(Menu menu, Order order) {
        this.menu = menu;
        this.order = order;
    }
    @Override
    public void setDrinks(){
        addDrinkToListOfOrderedDrinks();
    }

    @Override
    public void addDrinkToListOfOrderedDrinks() {
        OrderDrink orderDrink = new OrderDrink();
        List<OrderDrinkAdditive> listOfDrinkAdditivesForCurrentDrink = new ArrayList<>();
        orderDrink.setOrderNumber(order.getNumberOfOrder());
        int drinkId = Display.askForInt("Select ID of drink:");
        List<Integer> listOfAvailableDinksId = new ArrayList<>();
        addAllAvailableItemOrderIdFromMenuToList(listOfAvailableDinksId, menu.getListOfDrinks());
        while (!listOfAvailableDinksId.contains(drinkId)){
            drinkId = Display.askForInt("Please input correct ID of drink");
        }
        orderDrink.setDrinkId(drinkId);
        askUserForDrinkAdditives(orderDrink, listOfDrinkAdditivesForCurrentDrink);
        int amount = Display.askForInt("How many of those drinks?");
        orderDrink.setAmount(amount);
        order.addOrderDrinkToListOfOrderedDrinks(orderDrink);
    }

    public void addAllAvailableItemOrderIdFromMenuToList(List<Integer> listOfAvailableIds, List<MenuItem> listOfMenuItems) {
        for (MenuItem menuItem : listOfMenuItems) {
            listOfAvailableIds.add(menuItem.getId());
        }
    }

    public void askUserForDrinkAdditives(OrderDrink orderDrink, List<OrderDrinkAdditive> listOfDrinkAdditivesForCurrentDrink) {
        Display.printMessage("Would you like to add Drink Additive?");
        addDrinkAdditives(orderDrink,listOfDrinkAdditivesForCurrentDrink);
    }

    public void addDrinkAdditives(OrderDrink orderDrink, List<OrderDrinkAdditive> listOfDrinkAdditivesForCurrentDrink){
        Display.printFileContent("yesNoMenu");
        int addAnotherDrinkAdditive = Display.askForInt("Choose 1 or 2:");
        while(addAnotherDrinkAdditive!=1 && addAnotherDrinkAdditive!=2) {
            addAnotherDrinkAdditive = Display.askForInt("Choose 1 if Yes, 2 if No:");
        }
        if(addAnotherDrinkAdditive==1){
            addDrinkAdditivesToOrderedDrink(orderDrink, listOfDrinkAdditivesForCurrentDrink);
            Display.printMessage("Would you like to add more Drink Additive?");
            addDrinkAdditives(orderDrink , listOfDrinkAdditivesForCurrentDrink);
        } else {
            orderDrink.setListOfAdditives(listOfDrinkAdditivesForCurrentDrink);
        }
    }

    public void addDrinkAdditivesToOrderedDrink(OrderDrink orderDrink, List<OrderDrinkAdditive> listOfDrinkAdditivesForCurrentDrink){
            int drinkAdditiveId = Display.askForInt("Select ID of Drink Additive:");
            List<Integer> listOfAvailableDrinkAdditivesId = new ArrayList<>();
            addAllAvailableItemOrderIdFromMenuToList(listOfAvailableDrinkAdditivesId, menu.getListOfDrinkAdditives());
            while (!listOfAvailableDrinkAdditivesId.contains(drinkAdditiveId)){
                drinkAdditiveId = Display.askForInt("Please input correct id of Drink Additive");
            }
            OrderDrinkAdditive orderDrinkAdditive = new OrderDrinkAdditive();
            orderDrinkAdditive.setOrderNumber(order.getNumberOfOrder());
            orderDrinkAdditive.setDrinkAdditiveId(drinkAdditiveId);
            listOfDrinkAdditivesForCurrentDrink.add(orderDrinkAdditive);
            orderDrink.setListOfAdditives(listOfDrinkAdditivesForCurrentDrink);
    }
}
