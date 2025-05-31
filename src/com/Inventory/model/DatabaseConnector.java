/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jagrithnarayan
 */
// This class will be used to connect to our embedded databases

public class DatabaseConnector {

    // Store the database connection that will be used for the entire project
    private static Connection connection;
    
    // Allow external test code to override the connection (used in testing)
    public static void setTestConnection(Connection testConn) {
        connection = testConn;
    }


    // Private constructor to prevent multiple connections. We only want one
    private DatabaseConnector() {
        // Leave this empty
    }

    // Method to return the database connection. Will create one if it doesn't exist
     public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load the derby database driver
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                
                // Create a new database connection
                connection = DriverManager.getConnection("jdbc:derby:inventoryDB;create=true");
                
                // Print a success message to show that the user is connected to the database
                System.out.println("Connected to database: inventoryDB");
            } catch (ClassNotFoundException e) {
                System.out.println("Derby driver not found.");
            } catch (SQLException e) {
                // Print message if the program could not connect to the database
                System.out.println("Error connecting to main database");
            }
        }
        return connection;
    }

    // Method to close the database when the application closes
    public static void closeConnection() {
        // check if there is an active connection
        if (connection != null) {
            try {
                // if there is an active connection, close it and print a message to the user
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                // Print message if the database does not close for whatever reason
                System.out.println("Error when trying to close the connection");
            }
        }
    }
}
