package com.xformation.test.model.orders;

import com.xformation.test.model.Order;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    Order order;
    OrderDish dish;
    OrderDessert dessert;
    OrderDrink drink;

    @Before
    public void setUp() {
        dish = new OrderDish();
        dessert = new OrderDessert();
        drink = new OrderDrink();
        order = new Order();
        order.addOrderDishToListOfOrderedDishes(dish);
        order.addOrderDessertToListOfOrderedDesserts(dessert);
        order.addOrderDrinkToListOfOrderedDrinks(drink);
    }

    @Test
    public void testAddOrderDishToListOfOrderedDishes() {
        Assert.assertNotEquals(2, order.getListOfOrderedDrinks().size());
        Assert.assertEquals(1, order.getListOfOrderedDrinks().size());
    }

    @Test
    public void testAddOrderDessertToListOfOrderedDesserts() {
        Assert.assertNotEquals(2, order.getListOfOrderedDesserts().size());
        Assert.assertEquals(1, order.getListOfOrderedDesserts().size());
    }

    @Test
    public void testAddOrderDrinkToListOfOrderedDrinks() {
        Assert.assertNotEquals(2, order.getListOfOrderedDrinks().size());
        Assert.assertEquals(1, order.getListOfOrderedDrinks().size());
    }
}