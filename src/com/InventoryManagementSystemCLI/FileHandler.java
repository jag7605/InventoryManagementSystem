/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.InventoryManagementSystemCLI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jagrith Narayan
 */

// This class includes the functions to handle File I/O. 
// This includes a function to save inventory data to a txt file and a function to load inventory data from a file.
public class FileHandler {

    // The text file that will be storing the inventory data and its location. This name will not change hence the final keyword
    private static final String FILE_NAME = "./resources/inventory_data.txt";

    // method to save the current inventory to a txt file.
    public static void saveToFile(ArrayList<Product> inventory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product product : inventory) {
                writer.write(product.toString()); // convert the product to a string by calling the toString function
                writer.newLine(); // Move to a new line 
            }
            System.out.println("\nInventory saved to File.");
        } catch (IOException e) {
            System.out.println("Error saving inventory." + e.getMessage());
        }
    }

    // method to load inventory data from a file and return an array list of all of the products
    public static ArrayList<Product> loadFromFile() {
        ArrayList<Product> inventory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line; // variable to start at the beginning of the file
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // split the data wherever a comma appears
                int id = Integer.parseInt(data[0]); // the first element will be the prod id (convert the string to Integer using .parseInt)
                String name = data[1]; // the second will be the prod name
                String category = data[2]; // ... prod category 
                double price = Double.parseDouble(data[3]); // ... prod price (convert string to double using.parseDouble)
                int quantity = Integer.parseInt(data[4]); // ... prod quantity
                inventory.add(new Product(id, name, category, price, quantity)); // Add all of that data to the inventory.
            }
            System.out.println("Welcome to the Inventory Management System.\nExisting Inventory data has been successfully loaded from file.\n"); // Success message shown to user
        } catch (IOException e) {
            System.out.println("Welcome to the Inventory Management System.\nNo existing inventory data has been found. The program will start with an empty inventory.\n");
        }
        return inventory; // return the inventory after loading from file.
    }
}
