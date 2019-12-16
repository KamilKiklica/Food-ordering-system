package com.xformation.test.model.orders;

public class OrderDish extends OrderMenuItem{
    private int dishId;
    public OrderDish(int dishId, int amount) {
        super(amount);
        this.dishId = dishId;
    }
    public OrderDish(){
        super();
    }
    public int getDishId() {
        return dishId;
    }
}
