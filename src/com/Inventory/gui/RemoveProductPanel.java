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
public class RemoveProductPanel extends ProductPanelBase 
{
    private JTextField idField;
    private JButton removeButton, backButton;

    
    public RemoveProductPanel(ActionListener removeHandler, ActionListener backListener) 
    {
        setLayout(new GridLayout(6, 2, 10, 10));
        
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        add(new JLabel("Enter Product ID to Remove"));
        idField = new JTextField();
        add(idField);
        
        add(messageLabel); // Inherited from base class
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        
        backButton = new JButton("Back to Menu");
        backButton.setActionCommand("BACK");
        backButton.addActionListener(backListener);
        buttonPanel.add(backButton);
        
        removeButton = new JButton("Remove Product");
        removeButton.setActionCommand("REMOVE");
        removeButton.addActionListener(removeHandler);
        buttonPanel.add(removeButton);
        
        add(buttonPanel);
    }
    
    @Override
    public String getID() 
    {
        return idField.getText();
    }
    
    @Override
    public void clearFields(){
        idField.setText("");
    }
}
