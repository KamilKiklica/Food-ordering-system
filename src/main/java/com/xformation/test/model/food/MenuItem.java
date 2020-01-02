package com.xformation.test.model.food;

public abstract class MenuItem {
    private int id;
    private String name;
    private int price;

    public MenuItem(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
}
