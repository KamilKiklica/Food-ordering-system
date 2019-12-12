package com.xformation.test.model;

import com.xformation.test.model.dao.sql.food.DishesDAO;
import com.xformation.test.model.food.*;

import java.util.*;

public class Menu {
    private List<Cuisine> cuisines;
    private List<MenuItem> listOfDesserts;
    private List<MenuItem> listOfDrinks;
    private List<MenuItem> listOfDrinkAdditives;

    public Menu() {
        this.cuisines = new ArrayList<>();
    }
    public List<MenuItem> getListOfDrinks() {
        return this.listOfDrinks;
    }
    public List<MenuItem> getListOfDesserts() {
        return this.listOfDesserts;
    }
    public List<MenuItem> getListOfDrinkAdditives() {
        return listOfDrinkAdditives;
    }
    public List<Cuisine> getCuisines() {
        return this.cuisines;
    }

    public void setListOfDesserts(List<MenuItem> listOfDesserts) {
        this.listOfDesserts = listOfDesserts;
    }
    public void setListOfDrinks(List<MenuItem> listOfDrinks) {
        this.listOfDrinks = listOfDrinks;
    }
    public void setListOfDrinkAdditives(List<MenuItem> listOfDrinkAdditives) {
        this.listOfDrinkAdditives = listOfDrinkAdditives;
    }
    public void setCuisinesAndDishes(List<Cuisine> cuisinesList){
        this.cuisines = cuisinesList;
        for (Cuisine cuisine: this.cuisines) {
            cuisine.setListOfDishes(new DishesDAO().read(cuisine.getId()));
        }
    }
}
