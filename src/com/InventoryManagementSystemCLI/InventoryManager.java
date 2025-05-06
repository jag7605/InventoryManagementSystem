/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.InventoryManagementSystemCLI;

import java.util.ArrayList;

/**
 *
 * @author Jagrith Narayan
 */
// Class that inlcudes methods to add, remove and update products. 
// Also includes a constructor that will load any existing inventory data, a function to display the inventory,
// and additional methods to determine if products exist based on a given product ID or a given product name 
// (used when users try to remove/update a product)
public class InventoryManager {

    // set an array list that contains product objects. 
    public ArrayList<Product> inventory = new ArrayList<>();

    // Constructor for InventoryManager. 
    // This will set the inventory by loading any existing data via the loadFromFile function in the FileHandler class
    // If there is no existing data, the inventory will be empty and users can add data to it. 
    public InventoryManager() {
        this.inventory = FileHandler.loadFromFile();
    }

    // method to add a product to the inventory
    public void addProduct(Product product) {
        inventory.add(product);
        System.out.println("\nNew Product Added: " + product.getName());
        System.out.println("Product ID: " + product.getId());
        System.out.println("Product Category: " + product.getCategory());
        System.out.println("Product Price: " + String.format("%.2f", product.getPrice()));
        System.out.println("Product Quantity: " + product.getQuantity());
        FileHandler.saveToFile(inventory);
    }

    // method to remove a product based on its id
    public void removeProduct(int id) {
        inventory.removeIf(product -> product.getId() == id);
        FileHandler.saveToFile(inventory);
    }

    // method to check if a product exists based on its id 
    public boolean productExistsID(int productId) {
        
        for (Product product : inventory) {
            if (product.getId() == productId) {
                return true;
            }
        }
        return false;
    }

    // method to check if a product exists based on its name
    public boolean productExistsName(String name) {
        
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // method to update the details of a product. (get the product id and then change the price and/or the quantity.)
    public void updateProduct(int id, double newprice, int newquantity) {
        for (Product product : inventory) {
            if (product.getId() == id) {
                product.setPrice(newprice);
                product.setQuantity(newquantity);
                System.out.println("\nProduct Updated: " + product.getName());
                System.out.println("Updated Price: " + product.getPrice());
                System.out.println("Updated Stock level: " + product.getQuantity());
                FileHandler.saveToFile(inventory);
                return;
            }
        }

        System.out.println("Product not found");
    }

    // method to display all of the products in the inventory
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product product : inventory) {
                System.out.println(product + "\n");
            }
        }
    }

    // method that returns the inventory array list
    public ArrayList<Product> getInventory() {
        return inventory;
    }

}
