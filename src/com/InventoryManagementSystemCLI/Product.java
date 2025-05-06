/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.InventoryManagementSystemCLI;

/**
 *
 * @author Jagrith Narayan
 */
// Product class to set the details of the product
public class Product {

    // Initialising variables 
    private int id; // Unique identifier
    private String name; // Product name
    private String category; // Category of the product
    private double price; // Price of the product  
    private int quantity; // Quantity of the product

    // Constructor (generated using built-in code generators in net-beans. Right click -> Insert code -> Constructor)
    public Product(int id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters (Also generated with the same method as above)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // toString method 
    @Override
    public String toString() {
        return id + "," + name + "," + category + "," + price + "," + quantity;
    }

}
