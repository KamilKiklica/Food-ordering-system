package com.xformation.test.model.orders;

public class OrderDrinkAdditive {
    private int id;
    private int drinkAdditiveId;
    private int orderedDrinkIdentifier;

    public OrderDrinkAdditive(int id, int drinkAdditiveId, int orderedDrinkIdentifier) {
        this.id = id;
        this.drinkAdditiveId = drinkAdditiveId;
        this.orderedDrinkIdentifier = orderedDrinkIdentifier;
    }

    public OrderDrinkAdditive(){
        super();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
