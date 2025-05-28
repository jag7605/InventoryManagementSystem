/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jagrithnarayan
 */
// DAO stands for Data Access Object. It’s a design pattern used to separate the code that interacts with the 
// database from the rest of the application logic 
// This class will be used to handle the database interactions for product management.
// Will include methods to add, remove and update products from the database.
// tetsing
public class ProductDAO {

    // Set the database connection
    private final Connection connection;

    // constructor to initialise the DAO with a database connection. Also creates the product table. 
    public ProductDAO() {
        // get the database connection from the DatabaseConnector class
        this.connection = DatabaseConnector.getConnection();
        // call the createProductTable method to ensure that a table exists on launch
        createProductTable();
    }

    // method to create the product table with its columns
    private void createProductTable() {
        // SQL statement to create a products table with the following columns:
        // ID (Primary key), Name, Category, price, quantity
        
        String createTableSQL = "CREATE TABLE products ("
            + "id INT PRIMARY KEY, "
            + "name VARCHAR(100), "
            + "category VARCHAR(100), "
            + "price DOUBLE, "
            + "quantity INT"
            + ")";

        try (Statement stmt = connection.createStatement()) {
            // Execute the sql statement to create the table
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            // print an error message if the table does not get created
            System.out.println("Error creating products table.");
        }
    }

    // method to add a new product to the table
    public void addProduct(Product product) {
        // The question marks represent values that will be inserted later
        String sql = "INSERT INTO products (id, name, category, price, quantity) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, product.getId()); // First ? = id (integer)
            pstmt.setString(2, product.getName()); // Second ? = name (string)
            pstmt.setString(3, product.getCategory()); // Third ? = category (string)
            pstmt.setDouble(4, product.getPrice()); // Fourth ? = price (double)
            pstmt.setInt(5, product.getQuantity()); // Fifth ? = quantity (integer)
            // execute the sql insert statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // print an error if the product wasn't added
            System.out.println("Error adding product");
        }
    }

    // method to remove a product based on a given id
    public void removeProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            // execute the sql delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // print an error if the product could not be removed
            System.out.println("Error removing product");
        }
    }

    // method to update a product's price and quantity based on a given id
    public void updateProduct(int id, double price, int quantity) {
        // SQL statement to update price and quantity for a specific product
        String sql = "UPDATE products SET price = ?, quantity = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Set the parameters:
            pstmt.setDouble(1, price);    // New price (first ?)
            pstmt.setInt(2, quantity);    // New quantity (second ?)
            pstmt.setInt(3, id);         // ID of product to update (third ?)

            // Execute the update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // print an error if the product could not be updated
            System.out.println("Error updating product");
        }
    }

    // method to list all products in the database
    public List<Product> getAllProducts() {
        // List to hold all products we retrieve
        List<Product> products = new ArrayList<>();

        // SQL to select all products
        String sql = "SELECT * FROM products";

        // Try-with-resources for both Statement and ResultSet
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) { // ResultSet will contain the product data retrieved from the database

            // Once the data is retireved, process each row in the result set
            while (rs.next()) {
                // Create Product object from current row data
                Product p = new Product(
                        rs.getInt("id"), // Get id
                        rs.getString("name"), // Get name
                        rs.getString("category"), // Get category
                        rs.getDouble("price"), // Get price
                        rs.getInt("quantity") // Get quantity
                );
                // Add product to our list
                products.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving products");
        }
        // Return the complete list (empty if error occurred)
        return products;
    }

    // method to return the details of a product based on a given id
    public Product findProductById(int id) {
        // SQL statement to select product with specific ID
        String sql = "SELECT * FROM products WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Set the ID parameter
            pstmt.setInt(1, id);

            // Execute query
            ResultSet rs = pstmt.executeQuery();

            // If we found a matching ID
            if (rs.next()) {
                // Create and return Product object from result data
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding product");
        }
        // Return null if product was not found or an error occurred
        return null;
    }
}
