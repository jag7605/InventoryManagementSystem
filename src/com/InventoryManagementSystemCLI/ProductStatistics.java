/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.InventoryManagementSystemCLI;

import com.Inventory.model.Product;
import java.util.ArrayList;

/**
 *
 * @author jagrithnarayan
 */

// Class that includes methods to calculate various product statistics (total stock value and find most expensive product in inventory)
public class ProductStatistics {

    // method to return the total price of everything in the stock inventory
    public static String calculateTotalStockValue(ArrayList<Product> inventory) {
        
        // check if inventory is empty
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
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

    // method to return the most expensive product that is in the inventory
    public static Product findMostExpensiveProduct(ArrayList<Product> inventory) {

        // check if the inventory has any products
        if (inventory.isEmpty()) {
            return null;
        }

        // set the default most expensive item to the first product in the inventory
        Product mostExpensive = inventory.getFirst();
        
        // loop through every item in the inventory and check if its price is greater than the current most expensive item.
        for (Product product : inventory) {
            if (product.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = product;
            }
        }
        // return the most expensive item
        return mostExpensive;
    }

}
