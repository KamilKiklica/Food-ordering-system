package com.xformation.test.view;

import com.xformation.test.model.Menu;
import com.xformation.test.model.Order;
import com.xformation.test.model.display.ReadFileContentForDisplay;
import com.xformation.test.model.food.*;
import com.xformation.test.model.orders.*;

import java.io.IOException;
import java.util.*;

public class Display {
    public static void printFileContent(String menuName) {
        System.out.println("Choose an option: ");
        ReadFileContentForDisplay.showMenu(menuName).forEach(System.out::println);
    }

    public static int askForInt(String question) {
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String out = scanner.next();
        while (!out.matches("[0-9]+")) {
            Display.printMessage("Use only numbers");
            out = scanner.next();
        }
        return Integer.valueOf(out);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void pressButtonToContinue() {
        try {
            printMessage("Press any key to continue...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String multiSign(int multiplication, String sign) {
        String out = "";
        for (int i = 0; i < multiplication; i++) {
            out += sign;
        }
        return out;
    }

    public static void printMenu(Menu menu){
        List<Cuisine> cuisines = menu.getCuisines();
        List<MenuItem> listOfDesserts = menu.getListOfDesserts();
        List<MenuItem> listOfDrinks = menu.getListOfDrinks();
        List<MenuItem> listOfDrinkAdditives = menu.getListOfDrinkAdditives();

        List<String> listWithNamesOfAllProducts  = new ArrayList<>();
        List<Integer> listWithPriceOfAllProducts = new ArrayList<>();

        for (Cuisine cuisine: cuisines) {
            addProductsNamesAndPricesToLists(listWithNamesOfAllProducts, listWithPriceOfAllProducts, cuisine.getListOfDishes());
        }
        addProductsNamesAndPricesToLists(listWithNamesOfAllProducts,listWithPriceOfAllProducts,listOfDesserts);
        addProductsNamesAndPricesToLists(listWithNamesOfAllProducts,listWithPriceOfAllProducts,listOfDrinks);
        addProductsNamesAndPricesToLists(listWithNamesOfAllProducts,listWithPriceOfAllProducts,listOfDrinkAdditives);

        String dessertCategory = "Desserts";
        String drinksCategory = "Drinks";
        String drinksAdditivesCategory = "Drinks Additives";

        int idColumnWidth = Integer.toString(listWithNamesOfAllProducts.size()).length();
        int nameColumnWidth = "Name".length();
        int priceColumnWidth = "Price".length();

        nameColumnWidth = setWidthOfProductNameColumn(nameColumnWidth,listWithNamesOfAllProducts);
        priceColumnWidth = setWidthOfProductPriceColumn(priceColumnWidth,listWithPriceOfAllProducts);


        for (Cuisine cuisine: cuisines) {
            printMenuItems(cuisine.getNameOfCuisine()+" Dishes",cuisine.getListOfDishes(),idColumnWidth,nameColumnWidth,priceColumnWidth);
        }
        printMenuItems(dessertCategory, listOfDesserts,idColumnWidth,nameColumnWidth,priceColumnWidth);
        printMenuItems(drinksCategory,listOfDrinks,idColumnWidth,nameColumnWidth,priceColumnWidth);
        printMenuItems(drinksAdditivesCategory, listOfDrinkAdditives,idColumnWidth,nameColumnWidth,priceColumnWidth);
    }

    public static void addProductsNamesAndPricesToLists(List<String> listWithNamesOfAllProducts, List<Integer> listWithPriceOfAllProducts, List<MenuItem> listOfProducts) {
        for (MenuItem item : listOfProducts){
            listWithNamesOfAllProducts.add(item.getName());
            listWithPriceOfAllProducts.add(item.getPrice());
        }
    }

    public static int setWidthOfProductNameColumn(int columnWidth, List<String> listWithNamesOfAllProducts) {
        for (String name : listWithNamesOfAllProducts){
            if(columnWidth<=name.length()){
                columnWidth=name.length();
            }
        }
        return columnWidth;
    }
    public static int setWidthOfProductPriceColumn(int columnWidth, List<Integer> listWithPricesOfAllProducts) {
        for (Integer price : listWithPricesOfAllProducts){
            if(columnWidth<=Integer.toString(price).length()){
                columnWidth=Integer.toString(price).length();
            }
        }
        return columnWidth;
    }

    public static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public static void showActualOrder(Menu menu, Order order){
        List<OrderDish> dishes = order.getListOfOrderedDishes();
        List<OrderDessert> desserts = order.getListOfOrderedDesserts();
        List<OrderDrink> drinks = order.getListOfOrderedDrinks();

        int totalPrice=0;
        System.out.println("------------------------------:");
        System.out.println("NAME/AMOUNT/PRICE in $");
        System.out.println("------------------------------:");
        System.out.println("ORDERED DISHES:");
        if(dishes.size()>0) {
            totalPrice = printAllOrderedDishes(menu, dishes, totalPrice);
        }
        System.out.println("ORDERED DESSERTS:");
        if(desserts.size()>0) {
            totalPrice = printAllOrderedDesserts(menu, desserts, totalPrice);
        }
        System.out.println("ORDERED DRINKS:");
        if(drinks.size()>0) {
            totalPrice = printAllOrderedDrinks(menu, drinks, totalPrice);
        }
        System.out.println("------------------------------:");
        System.out.println("Total price of your order = "+totalPrice+"$");
    }

    public static int printAllOrderedDrinks(Menu menu, List<OrderDrink> drinks, int totalPrice) {
        for (OrderDrink orderedDrink : drinks) {
            int drinkId = orderedDrink.getDrinkId();
            for (MenuItem drink : menu.getListOfDrinks()) {
                if (drinkId == drink.getId()) {
                    totalPrice += (drink.getPrice() * orderedDrink.getAmount());
                    System.out.println("-->"+drink.getName() + "/" + orderedDrink.getAmount() + "/" + (drink.getPrice() * orderedDrink.getAmount())+"$");
                    totalPrice = printAllOrderedDrinkAdditives(menu, totalPrice, orderedDrink,  orderedDrink.getAmount());
                }
            }
        }
        return totalPrice;
    }

    public static int printAllOrderedDrinkAdditives(Menu menu, int totalPrice, OrderDrink orderedDrink, int amountOfDrinks) {
        List<OrderDrinkAdditive> drinkAdditives = orderedDrink.getListOfAdditives();
        if(drinkAdditives.size()>0) {
            for (OrderDrinkAdditive orderedAdditive: drinkAdditives) {
                int drinkAdditiveId = orderedAdditive.getDrinkAdditiveId();
                for (MenuItem drinkAdditive : menu.getListOfDrinkAdditives()) {
                    if (drinkAdditiveId == drinkAdditive.getId()) {
                        totalPrice += (amountOfDrinks*drinkAdditive.getPrice());
                        System.out.println("----->" + drinkAdditive.getName() + "/" + (amountOfDrinks*drinkAdditive.getPrice()) + "$");
                    }

                }
            }
        }
        return totalPrice;
    }

    public static int printAllOrderedDesserts(Menu menu, List<OrderDessert> desserts, int totalPrice) {
        for (OrderDessert orderedDessert : desserts) {
            int dessertId = orderedDessert.getDessertId();
            for (MenuItem dessert : menu.getListOfDesserts()) {
                if (dessertId == dessert.getId()) {
                    totalPrice += (dessert.getPrice() * orderedDessert.getAmount());
                    System.out.println("-->"+dessert.getName() + "/" + orderedDessert.getAmount() + "/" + (dessert.getPrice() * orderedDessert.getAmount())+"$");
                }
            }
        }
        return totalPrice;
    }

    public static int printAllOrderedDishes(Menu menu, List<OrderDish> dishes, int totalPrice) {
        for (OrderDish orderedDish : dishes) {
            int dishId = orderedDish.getDishId();
            for (Cuisine cuisine : menu.getCuisines()) {
                for (MenuItem dish : cuisine.getListOfDishes()) {
                    if (dishId == dish.getId()) {
                        totalPrice += (dish.getPrice() * orderedDish.getAmount());
                        System.out.println("-->"+dish.getName() + "/" + orderedDish.getAmount() + "/" + (dish.getPrice() * orderedDish.getAmount())+"$");
                    }
                }
            }
        }
        return totalPrice;
    }
    public static void showPendingOrders(List<Order> list){
        System.out.println("Numbers of orders waiting for service:");
        list.forEach(element -> System.out.println(element.getNumberOfOrder()));
    }

    public static void printMenuItems(String categoryName, List<MenuItem> list, int idColumnWidth, int nameColumnWidth, int priceColumnWidth) {

        int form1 = idColumnWidth;
        int form2 = nameColumnWidth;
        int form3 = priceColumnWidth;

        String formater1 = "%" + form1 + "s";
        String formater2 = "%" + form2 + "s";
        String formater3 = "%" + form3 + "s";

        String output = "";

        String l1 = multiSign(form1, "─");
        String l2 = multiSign(form2, "─");
        String l3 = multiSign(form3, "─");
        output += "┌" + l1 + "─" + l2 + "─" + l3 + "┐\n";

        output += "│ "+centerString(form1+form2+form3, categoryName) + " │\n";

        output += "├" + l1 + "┬" + l2 + "┬" + l3 + "┤\n";


        String itemIdH = String.format(formater1, "Id");
        String itemNameH = String.format(formater2, "Name");
        String itemPriceH = String.format(formater3, "Price");


        output += "│" + itemIdH + "│" + itemNameH +"│" + itemPriceH + "│\n";

        output += "├" + l1 + "┼" + l2 + "┼" + l3 +"┤\n";


        for (MenuItem item : list) {

            String prodId = String.format(formater1, item.getId());
            String prodName = String.format(formater2, item.getName());
            String prodPrice = String.format(formater3, item.getPrice());

            output += "│" + prodId + "│" + prodName + "│" + prodPrice + "│\n";

        }
        output += "└" + l1 + "┴" + l2 + "┴" + l3 + "┘\n";

        System.out.print(output);
    }
}
