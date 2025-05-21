/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.controller;

import com.Inventory.model.InventoryManager;
import com.Inventory.model.Product;
import com.Inventory.model.ProductStatistics;
import java.util.List;

/**
 *
 * @author jagrithnarayan
 */

// this class will handle product related actions triggered by the gui. 
// Main class will call these methods to add, remove and update products instead of directly accessing the model package
public class ProductController {
    
    private final InventoryManager inventoryManager; // reference to inventory manager
    
    // constructor to create a new InventoryManager
    public ProductController() {
        this.inventoryManager = new InventoryManager();
    }
    
    // add a product to the inventory (call method from model package)
    public boolean addProduct(Product product) {
        
        // first check if the product already exists based on its id
        if (inventoryManager.productExistsID(product.getId())){
            System.out.println("Product ID already exists");
            return false;
        }
        
        // then we check if it exists based on the name
        if (inventoryManager.productExistsName(product.getName())){
            System.out.println("Product Name already exists");
            return false;
        }
        
        // if the product gets added successfully, return true
        inventoryManager.addProduct(product);
        return true;
    }
    
    // remove a product based on its id (call method from model package)
    public boolean removeProduct(int id) {
        // check if the product id exists
        if (!inventoryManager.productExistsID(id)) {
            System.out.println("Product ID not found.");
            return false;
        }
        
        // if product was removed, return true
        inventoryManager.removeProduct(id);
        return true;
    }
    
    // update a product based on its id (call method from model package)
    public boolean updateProduct(int id, double newPrice, int newQuantity) {
        if (!inventoryManager.productExistsID(id)) {
            System.out.println("Product ID not found.");
            return false;
        }
        
        // if product was updated, return true
        inventoryManager.updateProduct(id, newPrice, newQuantity);
        return true;
    }
    
    // method to get the list of all products (used to referesh the product table in the gui)
    public List<Product> getAllProducts() {
        return inventoryManager.getInventory();
    }
    
    // method to get the total stock value (called from model package again)
    public String getTotalStockValue() {
        return ProductStatistics.calculateTotalStockValue(inventoryManager.getInventory());
    }
    
    // method to get the most expensive product in the inventory (called from model)
    public Product getMostExpensiveProduct() {
        return ProductStatistics.findMostExpensiveProduct(inventoryManager.getInventory());
    }
}
