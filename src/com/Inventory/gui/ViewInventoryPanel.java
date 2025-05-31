/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import com.Inventory.model.Product;
/**
 *
 * @author lee71
 */

public class ViewInventoryPanel extends JPanel{
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton backButton;
    
    public ViewInventoryPanel(ActionListener backListener) {
        setLayout(new BorderLayout());
        
        // columns names for the table
        String[] columnNames = {"ID", "Name", "Category", "Price", "Quantity"};
        
        //0 rows initially
        tableModel = new DefaultTableModel(columnNames, 0);
        
        //table to show inventory 
        table = new JTable(tableModel); 
        
        //mzake the table scrollable
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        backButton = new JButton("Back to Menu");
        backButton.setActionCommand("BACK");
        backButton.addActionListener(backListener);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    // now fill the table witjh product data
    public void setProductList(List<Product> products)
    {
        tableModel.setRowCount(0);
        for (Product p : products)
        {
            Object[] row = 
            {
                p.getId(),
                p.getName(),
                p.getCategory(),
                String.format("%2f", p.getPrice()),
                p.getQuantity()
            };
            
            //add each product to new roll
            tableModel.addRow(row);
        }
    }
}
