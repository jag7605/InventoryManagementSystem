/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.Inventory.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jagrithnarayan
 */
public class RestockManagerTest {

    // Test case to verify low stock product detection
    @Test
    public void testCheckLowStock_returnsLowStockProducts() {
        // Create test inventory with mixed stock levels
        List<Product> inventory = new ArrayList<>();
        inventory.add(new Product(1, "Laptop", "Electronics", 1500.0, 10)); // Above threshold (not low)
        inventory.add(new Product(2, "USB Cable", "Electronics", 5.0, 3)); // Below threshold (low stock)
        inventory.add(new Product(3, "Mouse", "Electronics", 20.0, 2)); // Below threshold (low stock)
        inventory.add(new Product(4, "Keyboard", "Electronics", 30.0, 7)); // Above threshold (not low)

        // Run the low stock check
        List<Product> result = RestockManager.checkLowStock(inventory);

        // Check results
        assertEquals(2, result.size()); // Should find exactly 2 low stock items
        assertTrue(result.stream().anyMatch(p -> p.getId() == 2)); // Check that the USB Cable is included
        assertTrue(result.stream().anyMatch(p -> p.getId() == 3)); // And check that the Mouse is included
    }

    // Test case for empty inventory 
    @Test
    public void testCheckLowStock_emptyInventoryReturnsEmptyList() {
        // Create empty inventory
        List<Product> inventory = new ArrayList<>();

        // Run the low stock check
        List<Product> result = RestockManager.checkLowStock(inventory);

        // Check results
        assertNotNull(result); // Should return a list (not null)
        assertTrue(result.isEmpty()); // List should be empty
    }

    // Test case for when all items have sufficient stock
    @Test
    public void testCheckLowStock_allItemsAboveThresholdReturnsEmptyList() {
        // Create inventory with all items above the low stock threshold
        List<Product> inventory = new ArrayList<>();
        inventory.add(new Product(5, "Monitor", "Electronics", 250.0, 10));  
        inventory.add(new Product(6, "Tablet", "Electronics", 500.0, 6));   

        // Run the low stock check
        List<Product> result = RestockManager.checkLowStock(inventory);

        // Check that no low-stock items found
        assertTrue(result.isEmpty());
    }
}
