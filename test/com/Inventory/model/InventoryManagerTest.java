/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.Inventory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*; 

/**
 *
 * @author jagrithnarayan
 */
public class InventoryManagerTest {

    private static final String TEST_DB_NAME = "testDB2"; // separate from DAO test db
    private static InventoryManager inventoryManager; // reference to inventoryManager 

    // same as ProductDAO test where we create a new database for testing
    @BeforeClass
    public static void setUpClass() throws Exception {
        // load the derby driver
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        // Create a separate test DB for inventory manager tests
        Connection testConn = DriverManager.getConnection("jdbc:derby:" + TEST_DB_NAME + ";create=true");
        Statement stmt = testConn.createStatement();

        // Clean up any existing products table (will ignore this if it doesn't exist)
        try {
            stmt.executeUpdate("DROP TABLE products");
        } catch (Exception ignored) {}

        // Create fresh products table with required columns
        stmt.executeUpdate("CREATE TABLE products ("
                + "id INT PRIMARY KEY, "
                + "name VARCHAR(100), "
                + "category VARCHAR(100), "
                + "price DOUBLE, "
                + "quantity INT)");

        testConn.close();

        DatabaseConnector.setTestConnection(DriverManager.getConnection("jdbc:derby:" + TEST_DB_NAME));
        inventoryManager = new InventoryManager();
    }

    @Before
    public void setUp() {
        // Clear products before each test
        for (Product p : inventoryManager.getInventory()) {
            inventoryManager.removeProduct(p.getId());
        }
    }

    // testing add product method
    @Test
    public void testAddProduct() {
        // Create and add a test product
        Product p = new Product(10, "Test Item", "Tools", 25.0, 3);
        inventoryManager.addProduct(p);

        // Check that the product exists in inventory
        assertTrue(inventoryManager.productExistsID(10));
    }

    // testing remove product method
    @Test
    public void testRemoveProduct() {
        // Add a product first
        Product p = new Product(11, "Test Remove", "Tools", 15.0, 2);
        inventoryManager.addProduct(p);

        // Remove it and check that it's gone
        inventoryManager.removeProduct(11);
        assertFalse(inventoryManager.productExistsID(11));
    }

    // testing productExistsID method
    @Test
    public void testProductExistsID() {
        // Add a test product
        Product p = new Product(12, "Check ID", "Electronics", 100.0, 1);
        inventoryManager.addProduct(p);

        // Check both valid and invalid cases
        assertTrue(inventoryManager.productExistsID(12)); // valid
        assertFalse(inventoryManager.productExistsID(999)); // invalid (product with that id does not exist)
    }

    // testing productExistsName method
    @Test
    public void testProductExistsName() {
         // Add product with specific name
        Product p = new Product(13, "UniqueName", "Books", 10.0, 7);
        inventoryManager.addProduct(p);

        // complete name checks (including case-insensitive)
        assertTrue(inventoryManager.productExistsName("UniqueName"));
        assertTrue(inventoryManager.productExistsName("uniquename")); // Case-insensitive check
        assertFalse(inventoryManager.productExistsName("SomethingElse")); // Non-existent name
    }

    // testing update product method
    @Test
    public void testUpdateProduct() {
        // Add initial product
        Product p = new Product(14, "UpdateMe", "Category", 20.0, 5);
        inventoryManager.addProduct(p);

        // Update price and quantity
        inventoryManager.updateProduct(14, 30.0, 10);
        
        // Retrieve updated product using Java Streams
        Product updated = inventoryManager.getInventory().stream()
                                .filter(prod -> prod.getId() == 14)
                                .findFirst()
                                .orElse(null);

        // Check the updates
        assertNotNull(updated); // Product still exists
        assertEquals(30.0, updated.getPrice(), 0.01); // Price updated (with margin (0.01 margin of error))
    }

    // testing getInventory method
    @Test
    public void testGetInventory() {
        // Add two test products
        inventoryManager.addProduct(new Product(15, "Item A", "Misc", 5.0, 1));
        inventoryManager.addProduct(new Product(16, "Item B", "Misc", 8.0, 2));

        // Get all inventory and check the count
        List<Product> result = inventoryManager.getInventory();
        assertEquals(2, result.size()); // Should contain exactly 2 items
    }

    // Clean up the database after testing is complete
    @AfterClass
    public static void tearDownClass() throws Exception {
        // Connect to test DB for cleanup
        Connection testConn = DriverManager.getConnection("jdbc:derby:" + TEST_DB_NAME);
        Statement stmt = testConn.createStatement();

        // Drop the products table (ignore if already gone)
        try {
            stmt.executeUpdate("DROP TABLE products");
        } catch (Exception ignored) {}

        testConn.close();
    }
}
