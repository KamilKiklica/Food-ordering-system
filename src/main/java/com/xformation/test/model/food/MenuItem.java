package com.xformation.test.model.food;

public abstract class MenuItem {
    private int Id;
    private String name;
    private int price;

    public MenuItem(int id, String name, int price) {
        this.Id = id;
        this.name = name;
        this.price = price;
    }
    public int getId() {
        return Id;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
}
