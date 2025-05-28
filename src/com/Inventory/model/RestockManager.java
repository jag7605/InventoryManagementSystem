/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jagrithnarayan
 */
// This class includes a method to check if products need to get restocked
//tetsing
public class RestockManager {

    // set a final integer of 5. If a product's quantity is less than 5, it will be considered as low stock
    private static final int LOW_STOCK = 5;

    public static List<Product> checkLowStock(List<Product> inventory) {
        // Create an arraylist that will store all of the products that have less than 5 items in stock
        List<Product> lowStockItems = new ArrayList<>();

        // loop through the inventory and add the products to the list
        for (Product product : inventory) {
            if (product.getQuantity() < LOW_STOCK) {
                lowStockItems.add(product);
            }
        }
        return lowStockItems;
    }
}
