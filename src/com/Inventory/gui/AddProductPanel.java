/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.gui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jagrithnarayan
 */

// testing
public class AddProductPanel extends JPanel
{
    private JTextField idField, nameField, categoryField, priceField, quantityField;
    private JButton addButton, backButton;
    private JLabel messageLabel;
    
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
        
        // displaying message dk if we need it we can rmv it if me don't need it
        messageLabel = new JLabel("");
        // make the message label to panel
        add(messageLabel);
        
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
    public String getId() { return idField.getText();}
    public String getName() { return nameField.getText();}
    public String getCategory() { return categoryField.getText();}
    public String getPrice() { return priceField.getText();}
    public String getQuantity() { return quantityField.getText();}
    
    public void showMessage(String msg)
    {
    messageLabel.setText(msg);
    }
    
    public void clearFields()
    {
        idField.setText("");  // clear product id
        nameField.setText("");  // clear name field
        categoryField.setText("");  // clear category
        priceField.setText("");  // clear price
        quantityField.setText("");  // clear quantity
    }
}
