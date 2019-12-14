package com.xformation.test.model.orders;

import com.xformation.test.model.Order;
import com.xformation.test.model.food.Dessert;
import com.xformation.test.model.food.Dish;
import com.xformation.test.model.food.Drink;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    Order order = new Order();
    OrderDish dish = new OrderDish();
    OrderDessert dessert = new OrderDessert();
    OrderDrink drink = new OrderDrink();

    @Before
    public void setUp() throws Exception {
//        OrderDish dish = new OrderDish();
//        OrderDessert dessert = new OrderDessert();
//        OrderDrink drink = new OrderDrink();
//        Order order = new Order();
        order.addOrderDishToListOfOrderedDishes(dish);
        order.addOrderDessertToListOfOrderedDesserts(dessert);
        order.addOrderDrinkToListOfOrderedDrinks(drink);

    }

    @Test
    public void addOrderDishToListOfOrderedDishes() {
        Assert.assertNotEquals(2, order.getListOfOrderedDrinks().size());
        Assert.assertEquals(1, order.getListOfOrderedDrinks().size());

    }

    @Test
    public void addOrderDessertToListOfOrderedDesserts() {
    }

    @Test
    public void addOrderDrinkToListOfOrderedDrinks() {
    }
}