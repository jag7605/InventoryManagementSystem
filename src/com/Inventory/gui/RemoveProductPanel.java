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
public class RemoveProductPanel extends JPanel 
{
    private JTextField idField;
    private JButton removeButton, backButton;
    private JLabel messageLabel;
    
    public RemoveProductPanel(ActionListener removeHandler, ActionListener backListener) 
    {
        setLayout(new GridLayout(4, 2, 10, 10));
        
        add(new JLabel("Enter Product ID to Remove"));
        idField = new JTextField();
        add(idField);
        
        messageLabel = new JLabel();
        add(messageLabel);
        
        removeButton = new JButton("Remove Product");
        removeButton.setActionCommand("REMOVE");
        removeButton.addActionListener(removeHandler);
        add(removeButton);
        
        backButton = new JButton("Back to Menu");
        backButton.setActionCommand("BACK");
        backButton.addActionListener(backListener);
        add(backButton);
    }
    
    public String getID() 
    {
        return idField.getText();
    }
    
    public void showMessage(String msg)
    {
        messageLabel.setText(msg);
    }
    
    public void clearField(){
        idField.setText("");
    }
}
