package com.xformation.test.model.orders;

public class OrderDrinkAdditive {
    private int drinkAdditiveId;
    private int orderedDrinkIdentifier;

    public OrderDrinkAdditive(int drinkAdditiveId, int orderedDrinkIdentifier) {
        this.drinkAdditiveId = drinkAdditiveId;
        this.orderedDrinkIdentifier = orderedDrinkIdentifier;
    }

    public OrderDrinkAdditive(){
        super();
    }


    public int getDrinkAdditiveId() {
        return drinkAdditiveId;
    }

    public void setDrinkAdditiveId(int drinkAdditiveId) {
        this.drinkAdditiveId = drinkAdditiveId;
    }

    public int getOrderedDrinkIdentifier() {
        return orderedDrinkIdentifier;
    }

    public void setOrderedDrinkIdentifier(int orderedDrinkIdentifier) {
        this.orderedDrinkIdentifier = orderedDrinkIdentifier;
    }
}
