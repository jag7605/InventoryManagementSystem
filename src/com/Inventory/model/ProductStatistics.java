/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.model;

import java.util.List;

/**
 *
 * @author jagrithnarayan
 */
// Product Statistics class that will include methods to calculate and return different statistics based on the inventory 
public class ProductStatistics {

    // Method to calculate the total stock value of the current inventory
    public static String calculateTotalStockValue(List<Product> inventory) {

        // first check if inventory is empty
        if (inventory.isEmpty()) {
            return "0.00";
        }

        // set the initial value to 0.
        double totalValue = 0;

        // loop through every item in the inventory, multiply the quantity by the price of the product and add it to the total.
        for (Product product : inventory) {
            totalValue += product.getPrice() * product.getQuantity();
        }

        String formatted = String.format("%.2f", totalValue); // format the double value to two decimal points

        return formatted; // return the formatted value as a string
    }

    // Method to return the most expensive item in the inventory
    public static Product findMostExpensiveProduct(List<Product> inventory) {

        // check if the inventory is empty and return null if it is
        if (inventory.isEmpty()) {
            return null;
        }

        // set the default most expensive product to the first product in the inventory
        Product mostExpensive = inventory.getFirst();

        // loop through each item in the inventory and compare the prices with the current most expensive product
        // if a product that costs more than the current most expensive product is found, update mostExpensive to the current product
        for (Product product : inventory) {
            if (product.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = product;
            }
        }

        // after checking, return the most expensive product
        return mostExpensive;
    }
}
