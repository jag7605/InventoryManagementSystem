/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.gui;

/**
 *
 * @author lee71
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// tetsing
public class MainMenuPanel extends JPanel
{
    // method to create and configure buttons
    // label - text on button 
    // command - action command for the button
    // listener - actionlistner that handle the button clicks
    private void addButton(String label, String command, ActionListener listener)
    {
        JButton btn = new JButton(label);  // buttons with specific text
        btn.setActionCommand(command);  // set action command for event identifica
        btn.addActionListener(listener);  // Register the click handler
        add(btn);  // add the button to panel
    }
    
    // main menu panel 
    // actionHandler handle all button actions
    // if Admin
    public MainMenuPanel(ActionListener actionHandler, boolean isAdmin)
    {
        //set up vertical grid layout
        // o rows , 1 column , 10px horizontal and vertical gaps in between components
        setLayout(new GridLayout(0, 1, 10, 10));
        
        setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        
        // create title label 
        JLabel title = new JLabel("Main Menu", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);
        
        // add menu buttons to grant access to it
        addButton("View Inventory", "VIEW", actionHandler);  // Button to view products
        addButton("Total Stock Value", "VALUE", actionHandler);  // Button to view Total stock vlue
        addButton("Most Expensive Product", "EXPENSIVE", actionHandler);  // Button to view most expensive product
        
        if (isAdmin) // Only visible for admin
        {
            addButton("Add Product", "ADD", actionHandler);  // Button to add product
            addButton("Remove Product", "REMOVE", actionHandler);  // Button to Remove product
            addButton("Update Product", "UPDATE", actionHandler);  // Button to update product
            addButton("Check Restock Alerts", "ALERT", actionHandler);  // Button to Check restock 
        }
        
        addButton("Logout", "LOGOUT", actionHandler);  // Button for logout
        
    }
}
