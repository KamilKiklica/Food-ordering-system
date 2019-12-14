package com.xformation.test.model.orders;

import java.util.ArrayList;
import java.util.List;

public class OrderDrink extends OrderMenuItem {
    private int drinkId;
    private int drinkIdentifier;
    private List<OrderDrinkAdditive> listOfAdditives;

    public OrderDrink(int drinkIdentifier, int drinkId, int amount) {
        super(amount);
        this.drinkId = drinkId;
        this.drinkIdentifier = drinkIdentifier;
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

    public int getDrinkIdentifier() {
        return drinkIdentifier;
    }

    public void setDrinkIdentifier(int drinkIdentifier) {
        this.drinkIdentifier = drinkIdentifier;
    }
}
