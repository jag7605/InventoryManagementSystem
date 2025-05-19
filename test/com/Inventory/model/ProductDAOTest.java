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

// Test class for ProductDAO using a test database
public class ProductDAOTest {

    // Constant for test database name
    private static final String TEST_DB_NAME = "testDB";
    // DAO instance that will be tested
    private static ProductDAO dao;

    // Setup method that runs once before all tests
    @BeforeClass
    public static void setUpClass() throws Exception {
        // make sure the embedded Derby driver is loaded
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        // Create a connection to test database with create=true flag
        Connection testConn = DriverManager.getConnection("jdbc:derby:" + TEST_DB_NAME + ";create=true");
        // Create statement object for executing SQL
        Statement stmt = testConn.createStatement();

        // Try to drop the products table if it exists (ignore errors if it doesn't)
        try {
            stmt.executeUpdate("DROP TABLE products");
        } catch (Exception e) {
            // Ignore errors (e.g., table doesn't exist)
        }

        // Create fresh products table with required columns
        stmt.executeUpdate("CREATE TABLE products ("
                + "id INT PRIMARY KEY, "
                + "name VARCHAR(100), "
                + "category VARCHAR(100), "
                + "price DOUBLE, "
                + "quantity INT)");

        // Close the database connection
        testConn.close();
        
        // This allows us to use a testing database instead of connecting to the main one in the program
        DatabaseConnector.setTestConnection(DriverManager.getConnection("jdbc:derby:testDB"));

        // Initialise the DAO that will be tested (it should connect to our test DB)
        dao = new ProductDAO();
    }

    // Setup method that runs before each test case
    @Before
    public void setUp() {
        // Clear all data from the products table before each test
        for (Product p : dao.getAllProducts()) {
            dao.removeProduct(p.getId());
        }
    }

    // Test case for adding a product
    @Test
    public void testAddProduct() {
        // Create a test product
        Product p = new Product(1, "Test Mouse", "Electronics", 20.0, 10);
        // Add the product via DAO
        dao.addProduct(p);

        // Retrieve the product and check that it was added correctly
        Product fetched = dao.findProductById(1);
        assertNotNull(fetched);  // Check that the product exists
        assertEquals("Test Mouse", fetched.getName());  // Check that the name is correct
    }

    // Test case for removing a product
    @Test
    public void testRemoveProduct() {
        // Create and add a test product
        Product p = new Product(2, "Test Keyboard", "Electronics", 30.0, 5);
        dao.addProduct(p);

        // Remove the product and check if it's gone
        dao.removeProduct(2);
        Product fetched = dao.findProductById(2);
        assertNull(fetched);  // check that the product no longer exists
    }

    // Test case for updating a product
    @Test
    public void testUpdateProduct() {
        // Create and add a test product
        Product p = new Product(3, "Test Monitor", "Electronics", 200.0, 2);
        dao.addProduct(p);

        // Update the product's price and quantity
        dao.updateProduct(3, 150.0, 4);
        // Retrieve and verify the updates
        Product updated = dao.findProductById(3);
        assertEquals(150.0, updated.getPrice(), 0.01);  // check the new price (added 0.01 for the margin of error)
        assertEquals(4, updated.getQuantity());  // check the new quantity
    }

    // Test case for getting all products
    @Test
    public void testGetAllProducts() {
        // Add two test products
        dao.addProduct(new Product(4, "Test Item A", "Test", 10.0, 1));
        dao.addProduct(new Product(5, "Test Item B", "Test", 15.0, 2));

        // Get all products and verify count
        List<Product> all = dao.getAllProducts();
        assertEquals(2, all.size());  // check that we got exactly 2 products
    }

    // Test case for finding a product by ID
    @Test
    public void testFindProductById() {
        // Create and add a test product
        Product p = new Product(6, "Test Webcam", "Electronics", 45.0, 3);
        dao.addProduct(p);

        // Find the product and check its details
        Product fetched = dao.findProductById(6);
        assertNotNull(fetched);  // check that the product exists
        assertEquals("Test Webcam", fetched.getName());  // check if it is the correct name
    }

    // Cleanup method that runs once after all tests. Will clear the testing database 
    @AfterClass
    public static void tearDownClass() throws Exception {
        // Connect to the test database for cleanup
        Connection testConn = DriverManager.getConnection("jdbc:derby:" + TEST_DB_NAME);
        Statement stmt = testConn.createStatement();

        // Try to drop the products table (ignore errors if it fails)
        try {
            stmt.executeUpdate("DROP TABLE products");
        } catch (Exception e) {
            // Ignore errors if already dropped
        }

        // Close the database connection
        testConn.close();
    }
}
