/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.gui;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.Inventory.model.Product;
/**
 *
 * @author lee71
 */
// testing
public class RestockAlertPanel extends JPanel
{
    private JTextArea alertArea;
    
    public RestockAlertPanel()
    {
        setLayout(new BorderLayout());
        
        //create a non editable table for the user to view only
        alertArea = new JTextArea();
        alertArea.setEditable(false);
        alertArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        // add a scroll to the text area
        add(new JScrollPane(alertArea), BorderLayout.CENTER);
    }
    
    public void displayAlerts(List<Product> lowStockProducts)
    {
        // create a header for the alert display
        StringBuilder sb = new StringBuilder("Restock Alerts:\n\n");
        
        // loop through each low stock product 
        for(Product p: lowStockProducts)
        {
            // Format each product information in seperate line
            sb.append("ID: ").append(p.getId()) //ID
              .append(", Name: ").append(p.getName()) //Name
              .append(", Quantity: ").append(p.getQuantity()) //Quantity
              .append("\n");
        }
        
        alertArea.setText(sb.toString());
    }
}
