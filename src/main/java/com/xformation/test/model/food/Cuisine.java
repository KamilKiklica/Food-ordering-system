package com.xformation.test.model.food;

import java.util.List;

public class Cuisine {
    private int Id;
    private String nameOfCuisine;
    private List<MenuItem> listOfDishes;

    public Cuisine(int Id, String nameOfCuisine) {
        this.Id = Id;
        this.nameOfCuisine = nameOfCuisine;
    }

    public String getNameOfCuisine() {
        return nameOfCuisine;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<MenuItem> getListOfDishes() {
        return listOfDishes;
    }

    public void setListOfDishes(List<MenuItem> listOfDishes) {
        this.listOfDishes = listOfDishes;
    }
}
