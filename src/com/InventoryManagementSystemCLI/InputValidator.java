/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.InventoryManagementSystemCLI;

import java.util.Scanner;

/**
 *
 * @author Jin An Lee
 */
// This class includes functions that will validate the user input. 
// These functions will make sure that the user is entering the correct data type when prompted and prevent throwing exceptions
public class InputValidator {

    private static Scanner scan = new Scanner(System.in);

    // validate if the input is in between the maximum and minimum number
    // convert the user input (string) into an Integer
    public static int validateInt(String input, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Invalid input. Enter a number in between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number");
            }
            input = scan.nextLine();
        }
    }

    // Function to validate the user input whenever we are asking for a string input (asking for product name or category)
    public static String validateString(String input) {
        while (true) {
            input = input.trim();

            // Allow user to exit
            if (input.equalsIgnoreCase("exit")) {
                return "exit";
            }

            // Validate user input (make sure the string is not empty and is made up of characters
            if (!input.isEmpty() && input.matches("[a-zA-Z ]+")) {
                return input;
            }

            // Ask user again again if they enter the wrong input
            System.out.println("Invalid input. Please enter letters only (no numbers or symbols):");
            input = scan.nextLine();
        }
    }

    // Function to validate product price whenever the user is propted to enter a price. 
    // Converts the string input from the user into a Double and also includes a way for the user to exit and return to the menu
    public static double validatePrice(String input) {
        while (true) {
            if (input.equalsIgnoreCase("exit")) {
                return -1;
            }
            try {
                double price = Double.parseDouble(input);
                return price;
            } catch (NumberFormatException e) {
                System.out.println("invalid input, try again");
            }
            input = scan.nextLine();
        }
    }

    // Same logic as the function above but this one converts the string into an Integer. 
    public static int validateQuantity(String input) {
        while (true) {
            if (input.equalsIgnoreCase("exit")) {
                return -1;
            }
            try {
                int quantity = Integer.parseInt(input);
                return quantity;
            } catch (NumberFormatException e) {
                System.out.println("invalid input, try again");
            }
            input = scan.nextLine();
        }
    }

    // This function works exactly the same as validateQuantity so we just call that function here. 
    // Makes it easier to work with when we know exactly what we are validating when calling these in the main class.
    // Can get confusing if we call the validateQuantity function when we are validating an ID.
    public static int validateID(String input) {
        return validateQuantity(input);
    }
}
