package com.xformation.test.model.orders;

public class OrderDrinkAdditive {
    private int id;
    private int orderNumber;
    private int drinkAdditiveId;
    private int drinkId;

    public OrderDrinkAdditive(int id, int orderNumber, int drinkAdditiveId, int drinkId) {
        this.orderNumber = orderNumber;
        this.drinkAdditiveId = drinkAdditiveId;
        this.drinkId = drinkId;
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
    public int getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(int orderId) {
        this.orderNumber = orderId;
    }
    public int getDrinkAdditiveId() {
        return drinkAdditiveId;
    }
    public void setDrinkAdditiveId(int drinkAdditiveId) {
        this.drinkAdditiveId = drinkAdditiveId;
    }
    public int getDrinkId() {
        return drinkId;
    }
    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }
}
