package com.xformation.test.model.orders;

public abstract class OrderMenuItem {
    private int amount;

    public OrderMenuItem(int amount) {
        this.amount = amount;
    }
    public OrderMenuItem() { }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
