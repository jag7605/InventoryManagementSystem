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

// Class that includes methods to check if a product has low stock
public class RestockManager {

    // set a final integer of 5. If a product's quantity is less than 5, it will be considered as low stock
    private static final int LOW_STOCK = 5;

    public static void checkLowStock(ArrayList<Product> inventory) {
        // first check if the inventory is empty and print a message to the console
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            // if the inventory includes products, initialise a boolean called needsRestock and set it to false. 
            boolean needsRestock = false;

            // Check if any product needs restocking. If its quanity is less than low_stock, set the need restock status to true
            for (Product product : inventory) {
                if (product.getQuantity() < LOW_STOCK) {
                    needsRestock = true;
                    break;
                }
            }

            // Print appropriate message based on restock status
            if (needsRestock == true) {
                System.out.println("\nRestock Alert! These Products are running low:\n");
                for (Product product : inventory) {
                    if (product.getQuantity() < LOW_STOCK) {
                        System.out.println("- " + product.getName().toUpperCase() + " (Current stock = " + product.getQuantity() + " units)");
                    }
                }
            } else { 
                // if there are products in the inventory and they are all stocked up (at least 5 units), print a message to the user
                System.out.println("All products are stocked up (All items in the inventory have at least 5 units)");
            }
        }
    }
}
