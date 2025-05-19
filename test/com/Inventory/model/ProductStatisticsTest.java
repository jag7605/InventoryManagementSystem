/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.Inventory.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

// Unit tests for the ProductStatistics class.
public class ProductStatisticsTest {

    @Test
    public void testCalculateTotalStockValue() {
        List<Product> inventory = new ArrayList<>();
        inventory.add(new Product(1, "Keyboard", "Electronics", 50.0, 2));  // 100
        inventory.add(new Product(2, "Mouse", "Electronics", 25.0, 3));     // 75

        String result = ProductStatistics.calculateTotalStockValue(inventory);
        assertEquals("175.00", result);
    }

    @Test
    public void testCalculateTotalStockValue_EmptyInventory() {
        List<Product> emptyInventory = new ArrayList<>();
        String result = ProductStatistics.calculateTotalStockValue(emptyInventory);
        assertEquals("0.00", result);
    }

    @Test
    public void testFindMostExpensiveProduct() {
        List<Product> inventory = new ArrayList<>();
        inventory.add(new Product(1, "Pen", "Stationery", 1.5, 20));
        inventory.add(new Product(2, "Laptop", "Electronics", 999.99, 1));
        inventory.add(new Product(3, "Notebook", "Stationery", 3.0, 10));

        Product result = ProductStatistics.findMostExpensiveProduct(inventory);
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
    }

    @Test
    public void testFindMostExpensiveProduct_EmptyInventory() {
        List<Product> emptyInventory = new ArrayList<>();
        Product result = ProductStatistics.findMostExpensiveProduct(emptyInventory);
        assertNull(result);
    }
}
