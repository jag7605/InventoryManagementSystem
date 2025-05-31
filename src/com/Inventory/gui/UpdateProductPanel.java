/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 *
 * @author lee71
 */

public class UpdateProductPanel extends ProductPanelBase
{
    private JTextField idField, priceField, quantityField;
    private JButton updateButton, backButton;

   
    public UpdateProductPanel(ActionListener actionHandler, ActionListener backListener)
    {
        setLayout(new GridLayout(6, 2, 10, 10));
        
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        add(new JLabel("Product ID:"));
        idField = new JTextField();
        add(idField);
        
        add(new JLabel("New Price:"));
        priceField = new JTextField();
        add(priceField);
        
        add(new JLabel("New Quantity:"));
        quantityField = new JTextField();
        add(quantityField);
        
        add(messageLabel); // Inherited from base class
        add(new JLabel()); 
        
        backButton = new JButton("Back to Menu");
        backButton.setActionCommand("BACK");
        backButton.addActionListener(backListener);
        add(backButton);
        
        updateButton = new JButton("Update Product");
        updateButton.setActionCommand("UPDATE_PRODUCT");
        updateButton.addActionListener(actionHandler);
        add(updateButton);
    }
    
    @Override
    public String getID() {
        return idField.getText();
    }
    
    public String getPrice() {
        return priceField.getText();
    }
    public String getQuantity() {
        return quantityField.getText();
    }
    
    
    @Override
    public void clearFields() {
        idField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }
}
