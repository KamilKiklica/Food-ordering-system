package com.xformation.test.model.orders;

import java.util.ArrayList;
import java.util.List;

public class OrderDrink extends OrderMenuItem {
    private int drinkId;
    private List<OrderDrinkAdditive> listOfAdditives;

    public OrderDrink(int id, int orderNumber, int drinkId, int amount) {
        super(id, orderNumber,amount);
        this.drinkId = drinkId;
        this.listOfAdditives = new ArrayList<>();
    }

    public OrderDrink(){
        super();
    }
    public int getDrinkId() {
        return drinkId;
    }
    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }
    public List<OrderDrinkAdditive> getListOfAdditives() {
        return this.listOfAdditives;
    }
    public void setListOfAdditives(List<OrderDrinkAdditive> listOfAdditives) {
        this.listOfAdditives = listOfAdditives;
    }

}
