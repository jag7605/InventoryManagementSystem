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
// This class connects the GUI to the database.
// It uses methods from ProductDAO to handle all the product actions like add, update, and remove.
public class InventoryManager {
    
    // create a reference to ProductDAO to get access to database operations
    private final ProductDAO productDAO;
    
    // constructor to set the database
    public InventoryManager() {
        this.productDAO = new ProductDAO();
    }
    
    // method to add a new product to the database
    public void addProduct(Product product) {
        // call the addproduct function from ProductDAO to add it to the database
        productDAO.addProduct(product);
        System.out.println("\nNew Product Added: " + product.getName());
        System.out.println("Product ID: " + product.getId());
        System.out.println("Product Category: " + product.getCategory());
        System.out.println("Product Price: " + String.format("%.2f", product.getPrice()));
        System.out.println("Product Quantity: " + product.getQuantity());
    }
    
    // method to remove a product based on its id
    public void removeProduct(int id) {
        // call the removeproduct method from ProductDAO to remove it from the database
        productDAO.removeProduct(id);
        System.out.println("\nProduct with ID " + id + " has been removed.");
    }
    
    // method to check if a product exists based on a given id
    public boolean productExistsID(int id) {
        // call the findProductByID method from ProductDAO to check if it exists in the database
        return productDAO.findProductById(id) != null;
    }

    // method to check if a product exists based on a given name
    public boolean productExistsName(String name) {
        // call the getAllProducts method from ProductDAO to get a list of all products currently in the database. 
        // then set a variable that holds the list of products
        List<Product> allProducts = productDAO.getAllProducts();
        
        // after the list is created, move through the list and return true if the given name matches any of the
        // existing product names in the database
        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        // if there are no matches, return false
        return false;
    }

    // method to update a product's price and quantity by ID
    public void updateProduct(int id, double newPrice, int newQuantity) {
        // first check if the product that the user wants to update actually exists
        if (productExistsID(id)) {
            // if it does, call the updateProduct method from productDAO to update the product in the database
            productDAO.updateProduct(id, newPrice, newQuantity);
            Product updated = productDAO.findProductById(id);
            System.out.println("\nProduct Updated: " + updated.getName());
            System.out.println("Updated Price: " + updated.getPrice());
            System.out.println("Updated Stock level: " + updated.getQuantity());
        } else {
            System.out.println("Product not found");
        }
    }

    // Retrieve all products (used by GUI and/or product statistics classes)
    public List<Product> getInventory() {
        return productDAO.getAllProducts();
    }
}
