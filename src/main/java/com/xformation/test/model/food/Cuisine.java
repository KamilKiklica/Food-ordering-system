package com.xformation.test.model.food;

import java.util.List;

public class Cuisine {
    private int id;
    private String nameOfCuisine;
    private List<MenuItem> listOfDishes;

    public Cuisine(int id, String nameOfCuisine) {
        this.id = id;
        this.nameOfCuisine = nameOfCuisine;
    }

    public String getNameOfCuisine() {
        return nameOfCuisine;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MenuItem> getListOfDishes() {
        return listOfDishes;
    }

    public void setListOfDishes(List<MenuItem> listOfDishes) {
        this.listOfDishes = listOfDishes;
    }
}
