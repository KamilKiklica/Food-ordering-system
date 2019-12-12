package com.xformation.test.model.orders;

public class OrderDish extends OrderMenuItem{
    private int dishId;
    public OrderDish(int id, int orderNumber, int dishId, int amount) {
        super(id,orderNumber,amount);
        this.dishId = dishId;
    }
    public OrderDish(){
        super();
    }
    public int getDishId() {
        return dishId;
    }
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
}
