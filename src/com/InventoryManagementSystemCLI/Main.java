/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.InventoryManagementSystemCLI;

import java.util.Scanner;

/**
 *
 * @author Jin An Lee, Jagrith Narayan
 */
public class Main {

    private static InventoryManager inventoryManager = new InventoryManager(); // create new instance of InventoryManager
    private static Scanner scanner = new Scanner(System.in); // scanner to allow user input
    private static String currentUserRole; // store the current user role
    private static boolean isAdmin; // boolean to check if the user has the admin role

    // main function to handle the login, display the menu and take in user choices in a loop where the user can exit at any time 
    public static void main(String[] args) {
        loginUser();

        while (true) {
            displayMenu();
            int choice = InputValidator.validateInt(scanner.nextLine(), 0, isAdmin ? 7 : 3); // minimum is 0, max is 7 for admin and 3 for staff
            // (admins have more options to pick from)
            switch (choice) {
                case 0:
                    loginUser();
                    break;
                case 1:
                    System.out.println("\n---Inventory---\n");
                    inventoryManager.displayInventory();
                    break;
                case 2:
                    System.out.println("\n---Total stock value---\n");
                    System.out.println("$" + ProductStatistics.calculateTotalStockValue(inventoryManager.inventory));
                    break;
                case 3:
                    System.out.println("\n---Most expensive product---\n");
                    Product expensiveProduct = ProductStatistics.findMostExpensiveProduct(inventoryManager.inventory);
                    if (expensiveProduct != null) {
                        System.out.println("Product Name: " + expensiveProduct.getName().toUpperCase());
                        System.out.println("This item costs: $"+ String.format("%.2f", expensiveProduct.getPrice()));
                    }
                    else {
                        System.out.println("Inventory is empty");
                    }
                    
                    break;
                case 4:
                    if (isAdmin) {
                        UserProduct();
                    }
                    break;
                case 5:
                    if (isAdmin) {
                        UserRemoveProduct();
                    }
                    break;
                case 6:
                    if (isAdmin) {
                        UserUpdateProduct();
                    }
                    break;
                case 7:
                    if (isAdmin) {
                        RestockManager.checkLowStock(inventoryManager.getInventory());
                    }
                    break;

                default:
                    if (isAdmin) {
                        System.out.println("Invalid choice. Please enter a valid number (0 - 7).");
                    }
                    else {
                        System.out.println("Invalid choice. Please enter a valid number (0 - 3).");
                    }
                    
            }
        }
    }

    // function to set the user's role. 
    private static void loginUser() {
        while (true) {
            System.out.print("Enter Password (or type \"exit\" to shut down): "); // ask the user to enter a password
            String password = scanner.nextLine();

            if (password.equalsIgnoreCase("exit")) { // if they type "exit", terminate the program
                System.out.println("Exiting program...");
                System.exit(0);
            }

            // if the password entered matches either the admin or staff password, 
            // store the given role in a variable
            String role = UserManager.Permit(password);

            if (role != null) {
                System.out.println("\nLogged in successfully as: " + role); // print out a success message if the password is correct
                currentUserRole = role; // store the role 
                isAdmin = currentUserRole.equals("admin"); // if the stored role is admin then set isAdmin to true to allow admin perms
                return;
            } else {
                System.out.println("Wrong password, try again"); // if role is empty then the password was incorrect since nothing was stored
            }
        }
    }

    // function to display the menu and a prompt to ask the user what they would like to do
    // The menu will include more options if the user is logged in as admin
    private static void displayMenu() {
        System.out.println("\n<< Inventory Managment System >>");
        System.out.println("\n0. Log out");
        System.out.println("1. View Inventory");
        System.out.println("2. Total Stock Value");
        System.out.println("3. Most Expensive Product");
        if (isAdmin) { // these options will only appear of the user is logged in as admin
            System.out.println("4. Add Product");
            System.out.println("5. Remove Product");
            System.out.println("6. Update Product");
            System.out.println("7. Check Restock Alerts");
        }
        System.out.println("\nEnter your choice: ");
    }

    // Function that will ask the admin to enter new product details to add to inventory
    // The function will validate the user input with functions from the Inputvalidator class 
    // and the inventorymanager class. A new product will be added to the inventory if all of the inputs are valid.
    private static void UserProduct() {
        System.out.println("\n---Adding Product---");

        System.out.println("Enter \"exit\" to return to menu");

        // ask for product id and validate
        System.out.println("Enter Product ID: ");
        int id = InputValidator.validateID(scanner.nextLine());
        if (id == -1) {
            return;
        }
        if (inventoryManager.productExistsID(id)) {
            System.out.println("\nError: Product ID already exists. Try again:");
            return; // Exit method 
        }

        // ask for product name and validate
        System.out.println("Enter Product Name: ");
        String name = scanner.nextLine();
        name = InputValidator.validateString(name);
        if (name.equalsIgnoreCase("exit")) {
            return;
        }
        if (inventoryManager.productExistsName(name)) {
            System.out.println("\nError: Product Name already exists.");
            return; // Exit method 
        }

        // ask for product categrory and validate 
        System.out.println("Enter Product Category: ");
        String category = scanner.nextLine();
        category = InputValidator.validateString(category);
        if (category.equalsIgnoreCase("exit")) {
            return;
        }

        // ask user for product price and validate 
        System.out.println("Enter Product Price: ");
        double price = InputValidator.validatePrice(scanner.nextLine());
        if (price == -1) {
            return;
        }

        // ask user for product quantity and validate 
        System.out.println("Enter Product Quantity: ");
        int quantity = InputValidator.validateQuantity(scanner.nextLine());
        if (quantity == -1) {
            return;
        }
        
        // if all of the data entered is valid, create a new Product object with the given data and add it to the inventory
        Product product = new Product(id, name, category, price, quantity);
        inventoryManager.addProduct(product);
    }

    // Method to remove a product from the inventory. Will prompt the user to enter the product id of the item that they would like to remove
    // The function will check if the given id is valid and if the product that they are trying to remove actually exists. 
    private static void UserRemoveProduct() {
        if (inventoryManager.inventory.isEmpty()) {
            System.out.println("Inventory is empty. Nothing to remove.");
        } else {

            System.out.println("\n---Removing Product---");
            System.out.println("Enter \"exit\" to return to menu");
            System.out.println("Enter Product ID: ");

            // Get a valid product ID using your validateID function 
            int removeID = InputValidator.validateID(scanner.nextLine());
            if (removeID == -1) {
                return;
            }

            // Check if the product exists in the inventory 
            if (!inventoryManager.productExistsID(removeID)) {
                System.out.println("\nError: Product ID not found. Please enter a valid ID.");
                return; // Exit method without removing anything 
            }
            // Remove the product if it exists 
            inventoryManager.removeProduct(removeID);
            System.out.println("\nProduct with ID '" + removeID + "' removed successfully.");
        }
    }

    // Function that allows the user to update the price and quantity details of an existing product. (Track price changes and stock levels)
    // Uses functions from input validator class for validation
    private static void UserUpdateProduct() {
        if (inventoryManager.inventory.isEmpty()) {
            System.out.println("Inventory is empty. Nothing to update.");
        } else {

            System.out.println("\n---Updating Product---");
            System.out.println("Enter \"exit\" to return to menu");
            System.out.println("Enter product ID: ");

            // Get a valid product ID using your validateID function 
            int updateID = InputValidator.validateID(scanner.nextLine());
            if (updateID == -1) {
                return;
            }

            // Check if the product exists in the inventory 
            if (!inventoryManager.productExistsID(updateID)) {
                System.out.println("\nError: Product ID not found. Please enter a valid ID.");
                return; // Exit method without removing anything 
            }

            // otherwise ask the user to enter the updated price and quantity
            System.out.println("Enter new price: ");
            double newPrice = InputValidator.validatePrice(scanner.nextLine());
            if (newPrice == -1) {
                return;
            }

            System.out.println("Enter new quantity: ");
            int newQuantity = InputValidator.validateQuantity(scanner.nextLine());
            if (newQuantity == -1) {
                return;
            }
            
            // call the update product method and insert the data that has been entered
            inventoryManager.updateProduct(updateID, newPrice, newQuantity);

        }
    }
}
