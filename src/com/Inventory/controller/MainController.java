/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.controller;

import com.Inventory.gui.*;
import com.Inventory.model.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
/**
 *
 * @author lee71
 */

public class MainController {
    private final PanelManager panelManager;
    private final ProductController productController;
    private final RestockController restockController;
    private final LoginPanel loginPanel;
    private MainMenuPanel mainMenuPanel;
    
    private final AddProductPanel addPanel;
    private final ViewInventoryPanel viewPanel;
    private final UpdateProductPanel updatePanel;
    private final RemoveProductPanel removePanel;
    private final RestockAlertPanel restockPanel;
    
    private boolean isAdmin;
    
    public MainController(JFrame frame)
    {
        productController = new ProductController();
        restockController = new RestockController(productController);
        
        panelManager = new PanelManager(frame);
        
        // create and add log in panel
        loginPanel = new LoginPanel(new LoginHandler());
        panelManager.addPanel("login", loginPanel);
        
        // craete and add add product panels
        addPanel = new AddProductPanel(new AddProductListener(), new BackToMenuListener());
        panelManager.addPanel("addProduct", addPanel);

        // create and add view inventory panels
        viewPanel = new ViewInventoryPanel(new BackToMenuListener());
        panelManager.addPanel("viewInventory", viewPanel);

        updatePanel = new UpdateProductPanel(new UpdateProductListener(), new BackToMenuListener());
        panelManager.addPanel("updateProduct", updatePanel);

        removePanel = new RemoveProductPanel(new RemoveProductListener(), new BackToMenuListener());
        panelManager.addPanel("removeProduct", removePanel);

        restockPanel = new RestockAlertPanel(new BackToMenuListener());
        panelManager.addPanel("restock", restockPanel);
        
        // show login screen
        panelManager.showPanel("login");
    }
    
    //handle login button click
    private class LoginHandler implements ActionListener
    {
        @Override 
        public void actionPerformed(ActionEvent e)
        {
            String password = loginPanel.getPassword();
            String role = UserManager.permit(password);
            
            if(role != null)
            {
                // determine if the user is admin
                isAdmin = role.equalsIgnoreCase("admin");
                
                // initialize main menu with proper permissions
                mainMenuPanel = new MainMenuPanel(new MenuActionHandler(), isAdmin);
                panelManager.addPanel("menu", mainMenuPanel);
                
                panelManager.showPanel("menu");
            }
            else
            {
                loginPanel.showMessage("Invalid password. Try again.");
            }
        }
    }
    
    // this is use to return to main menu when you click the button "BACK"
    private class BackToMenuListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            panelManager.showPanel("menu");
        }
    }
    
    // Handles button actions from MainMenuPanel
    private class MenuActionHandler implements ActionListener
    {
        @Override 
        public void actionPerformed(ActionEvent e)
        {
            String cmd = e.getActionCommand();
            
            switch (cmd)
            {
                case "VIEW":
                    // Get product list from controller
                    List<Product> products = productController.getAllProducts();
                    
                    // view panel from panel manager
                    viewPanel.setProductList(products);
                    panelManager.showPanel("viewInventory");
                    break;
                    
                case "ADD" :
                    panelManager.showPanel("addProduct");
                    break;
                    
                case "REMOVE" :
                    panelManager.showPanel("removeProduct");
                    break;
                    
                case "ALERT" :
                    List<Product> lowStock = restockController.getLowStockProducts();
                    restockPanel.displayAlerts(lowStock);
                    panelManager.showPanel("restock");
                    break;
                    
                case "VALUE" :
                    String totalValue = productController.getTotalStockValue();
                    JOptionPane.showMessageDialog(null, "Total Stock Value: $" +totalValue);
                    break;
                    
                case "UPDATE":
                    panelManager.showPanel("updateProduct");
                    break;
                    
                case "EXPENSIVE" :
                    Product p = productController.getMostExpensiveProduct();
                    if(p != null)
                    {
                        JOptionPane.showMessageDialog(null, "Most Expensive Product:\nName: " + p.getName() + 
                                "\nPrice: $" + String.format("%.2f", p.getPrice()));
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No Products in inventory.");
                    }
                    break;
                
                case "LOGOUT" :
                    panelManager.showPanel("login");
                    break;
            }
        }
    }
    
    // handles add product from submission
    private class AddProductListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try{
                int id = Integer.parseInt(addPanel.getID());
                String name = addPanel.getName();
                String category = addPanel.getCategory();
                double price = Double.parseDouble(addPanel.getPrice());
                int qty = Integer.parseInt(addPanel.getQuantity());
                
                // error handling make sure category doesn't have numbers
                if (!category.matches("[a-zA-Z ]+")) 
                {
                    addPanel.showMessage("Category must contain only letters.");
                    return;
                }
                
                // create and add product 
                Product product = new Product(id, name, category, price, qty);
                if(productController.addProduct(product))
                {
                    // product added successfully msg
                    addPanel.showMessage("Product added successfully.");
                    addPanel.clearFields();
                }
                else
                {
                    // product already exist msg
                    addPanel.showMessage("Product already exists");
                }
            } catch (NumberFormatException ex)
            {
                addPanel.showMessage("Invalid input.");
            }
        }
    }
    
    // handle product updating submission
    private class UpdateProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(updatePanel.getID());
                double price = Double.parseDouble(updatePanel.getPrice());
                int qty = Integer.parseInt(updatePanel.getQuantity());

                if (productController.updateProduct(id, price, qty)) 
                {
                    // product succefully updated
                    updatePanel.showMessage("Product updated.");
                    updatePanel.clearFields();
                } 
                
                else 
                {
                    // if id doesn't exist msg
                    updatePanel.showMessage("Product not found. Please enter a valid Product ID");
                }
                
            } catch (NumberFormatException ex) 
            {
                updatePanel.showMessage("Invalid input. Please enter numbers in these fields.");
            }
        }
    }
    
    // handles product removing submission
    private class RemoveProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(removePanel.getID());
                if (productController.removeProduct(id)) 
                {
                    // product remove successfully msg
                    removePanel.showMessage("Product removed.");
                    removePanel.clearFields();
                } 
                
                else 
                {
                    // product id doesn't exist msg
                    removePanel.showMessage("Product ID not found. Please enter a valid Product ID");
                }
            } catch (NumberFormatException ex) 
            {
                removePanel.showMessage("Invalid input. Please enter a valid Product ID");
            }
        }
    }
}
