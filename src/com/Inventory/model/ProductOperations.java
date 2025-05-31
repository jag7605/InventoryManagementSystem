/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Inventory.model;

import java.util.List;

/**
 *
 * @author jagrithnarayan
 */
// The product operations interface lists the basic product actions (add, remove, update, get) for our inventory management system.
// this will be used by the InventoryManager class. 
public interface ProductOperations {

    void addProduct(Product product);
    void removeProduct(int id);
    boolean productExistsID(int id);
    boolean productExistsName(String name);
    void updateProduct(int id, double price, int quantity);
    List<Product> getInventory();
    
}
