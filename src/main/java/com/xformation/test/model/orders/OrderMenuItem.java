package com.xformation.test.model.orders;

public abstract class OrderMenuItem {
    private int id;
    private int orderNumber;
    private int amount;

    public OrderMenuItem(int id, int orderNumber, int amount) {
        this.orderNumber = orderNumber;
        this.amount = amount;
    }

    public OrderMenuItem() { }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
