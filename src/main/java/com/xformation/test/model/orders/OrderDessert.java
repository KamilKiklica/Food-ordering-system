package com.xformation.test.model.orders;

public class OrderDessert extends OrderMenuItem{
    private int dessertId;

    public OrderDessert(int dessertId, int amount) {
        super(amount);
        this.dessertId = dessertId;
    }
    public OrderDessert(){
        super();
    }
    public int getDessertId() {
        return this.dessertId;
    }
    public void setDessertId(int dessertId) {
        this.dessertId = dessertId;
    }

}
