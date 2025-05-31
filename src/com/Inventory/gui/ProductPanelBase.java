/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.gui;
import javax.swing.*;

/**
 *
 * @author jagrithnarayan
 */

// Abstract base class for product-related GUI panels. Supports inheritance and polymorphism with a common interface
// for product input tasks (add, update, remove).
public abstract class ProductPanelBase extends JPanel {

    // Shared label to display error/success messages
    protected JLabel messageLabel;

    // initialise the shared message label in a constructor
    public ProductPanelBase() {
        messageLabel = new JLabel();
    }

    // abstract method where all subclasses must have a way to get the product id from the user input
    public abstract String getID(); 

    // concrete method that sets a message in the message label. 
    // the add, remove and update panel classes can use this method directly
    public void showMessage(String msg) {
        if (messageLabel != null) {
            messageLabel.setText(msg);
        }
    }

    // subclasses will override this method to clear their own input fields
    public void clearFields() {
        // this will be left empty here 
    }
}
