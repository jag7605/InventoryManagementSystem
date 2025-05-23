/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.controller;

import com.Inventory.model.InventoryManager;
import com.Inventory.model.Product;
import com.Inventory.model.RestockManager;
import java.util.List;

/**
 *
 * @author jagrithnarayan
 */
// this class handles the restock logic. Will return a list of products that need to be restocked for the gui to display
public class RestockController {
    
     private final ProductController productController; // reference to inventory manager
     
     // constructor to create a new InventoryManager
    public RestockController(ProductController productController) {
    this.productController = productController;
    }
    
    // method to return a list of all products that need to be restocked
    public List<Product> getLowStockProducts() {
        return RestockManager.checkLowStock(productController.getAllProducts());
    }  
}
