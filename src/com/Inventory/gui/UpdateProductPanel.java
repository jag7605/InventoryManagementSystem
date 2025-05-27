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
 * @author jagrithnarayan
 */
public class UpdateProductPanel extends JPanel
{
    private JTextField idField, priceField, quantityField;
    private JButton updateButton, backButton;
    private JLabel messageLabel;
   
    public UpdateProductPanel(ActionListener actionHandler, ActionListener backListener)
    {
        setLayout(new GridLayout(5, 2, 10, 10));
        
        add(new JLabel("Product ID:"));
        idField = new JTextField();
        add(idField);
        
        add(new JLabel("New Price:"));
        priceField = new JTextField();
        add(priceField);
        
        add(new JLabel("New Quantity:"));
        quantityField = new JTextField();
        add(quantityField);
        
        updateButton = new JButton("Update Product");
        updateButton.setActionCommand("UPDATE_PRODUCT");
        updateButton.addActionListener(actionHandler);
        add(updateButton);
        
        backButton = new JButton("Back to Menu");
        backButton.setActionCommand("BACK");
        backButton.addActionListener(backListener);
        add(backButton);
    }
    
    public String getID() {
        return idField.getText();
    }
    public String getPrice() {
        return idField.getText();
    }
    public String getQuantity() {
        return idField.getText();
    }
    public void showMessage(String msg)
    {
        messageLabel.setText(msg);
    }
    
    public void clearFields()
    {
        idField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }
}
