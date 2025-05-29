/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.gui;

/**
 *
 * @author jagrithnarayan
 */
import javax.swing.*;  // Import Swing components like JPanel, JLabel, JButton
import java.awt.*;  // layout managers
import java.awt.event.ActionListener; // button clicks to be ahndled with an external listener

// testing
public class LoginPanel extends JPanel
{
    private JTextField passwordField;  // Input field for password
    private JButton loginButton;  // Button to trigger login attempt
    private JLabel messageLabel; // show success or error messages
    
    public LoginPanel(ActionListener LoginHandler) 
    // ActionListener(Button clicks, menu item selection)
    // LoginHandler (login related action(when user clicks "Login")
    {
        setLayout(new GridBagLayout()); // gridbaglayout is one of the felxible layout use to manage the login page
        GridBagConstraints gbc = new GridBagConstraints(); //object to store layput rules
        
        
        // Title Label
        JLabel title = new JLabel("Inventory Management System"); // Title tex
        title.setFont(new Font("Arial", Font.BOLD, 18));  // set font size and style
        gbc.gridx = 0;  // X position
        gbc.gridy = 0;  // Y position
        gbc.gridwidth = 2;  // span accross 2 columns
        gbc.insets = new Insets(10, 10, 10, 10); // add padding
        add(title, gbc);  // add to panel
        
        // Label prompting user to enter password 
        JLabel passwordLabel = new JLabel("Enter Password:");
        gbc.gridx = 0; 
        gbc.gridy = 1;  // next row
        gbc.gridwidth = 1;  // normal width
        add(passwordLabel, gbc);
        
        // input field for password
        passwordField = new JTextField(15);
        gbc.gridx = 1; // x = 1 (next to Label)
        add(passwordField, gbc);
        
        // login button
        loginButton = new JButton("Login");  // create button with label
        loginButton.setActionCommand("LOGIN"); // set an action to identify
        loginButton.addActionListener(LoginHandler); // connect to the listener
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;  // ceonter the button under both field
        add(loginButton, gbc);
        
        // Message Lebel for shwoing error or success
        messageLabel = new JLabel("");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 3;  // row 3
        gbc.gridwidth = 2;
        add(messageLabel, gbc);
    }
    
    public String getPassword() {
        return passwordField.getText();
    }
    
    public void showMessage(String msg) {
        messageLabel.setText(msg);
    }
}
