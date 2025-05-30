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
public class RemoveProductPanel extends JPanel 
{
    private JTextField idField;
    private JButton removeButton, backButton;
    private JLabel messageLabel;
    
    public RemoveProductPanel(ActionListener removeHandler, ActionListener backListener) 
    {
        setLayout(new GridLayout(6, 2, 10, 10));
        
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        add(new JLabel("Enter Product ID to Remove"));
        idField = new JTextField();
        add(idField);
        
        messageLabel = new JLabel();
        add(messageLabel);
        
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
