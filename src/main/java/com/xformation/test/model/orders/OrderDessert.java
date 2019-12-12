package com.xformation.test.model.orders;

public class OrderDessert extends OrderMenuItem{
    private int dessertId;

    public OrderDessert(int id, int orderNumber,  int dessertId, int amount) {
        super(id, orderNumber, amount);
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
