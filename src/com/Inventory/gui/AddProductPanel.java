/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.gui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

/**
 *
 * @author lee71
 */


public class AddProductPanel extends ProductPanelBase
{
    private JTextField idField, nameField, categoryField, priceField, quantityField;
    private JButton addButton, backButton;

    
    public AddProductPanel(ActionListener actionHandler, ActionListener backListener) 
    {
        // layout with 7 rows 2 collums
        setLayout(new GridLayout(7, 2, 10, 10));
        
        // Add padding to the panel directly
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        // label for product id field
        add(new JLabel("Product ID"));
        
        // create test field for product io input
        idField = new JTextField();
        
        // add the field to the panel
        add(idField);
        
        add(new JLabel("Name:"));
        nameField= new JTextField();
        add(nameField);
        
        add(new JLabel("Category:"));
        categoryField = new JTextField();
        add(categoryField);
        
        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);
        
        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);
        
        
        // make the message label to panel
        // messagelabel was inherited from ProductPanelBase class
        add(messageLabel); // initialised in the base class constructor
        
        // create addProduct button
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        
        backButton = new JButton("Back to Menu");
        backButton.setActionCommand("BACK");
        backButton.addActionListener(backListener);
        buttonPanel.add(backButton);
        
        addButton = new JButton("Add Product");
        // command action for the button 
        addButton.setActionCommand("APP_PRODUCT");
        // action listener when button clicks
        addButton.addActionListener(actionHandler);
        // add the button to panel 
        buttonPanel.add(addButton);
        
        add(buttonPanel);
    }
    
    // getter method to retrieve product id from textField
    // overriden from the base class
    @Override
    public String getID() { return idField.getText(); }
    
    public String getName() { return nameField.getText(); }
    public String getCategory() { return categoryField.getText(); }
    public String getPrice() { return priceField.getText(); }
    public String getQuantity() { return quantityField.getText(); }
    
    // override the clear field method to clear the fields that only appear in the add product panel
    @Override
    public void clearFields() {
        idField.setText("");
        nameField.setText("");
        categoryField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }
}
