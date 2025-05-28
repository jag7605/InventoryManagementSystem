/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.Inventory.model.Product;
/**
 *
 * @author jagrithnarayan
 */
//testing
public class ViewInventoryPanel extends JPanel{
    private JTable table;
    private DefaultTableModel tableModel;
    
    public ViewInventoryPanel() {
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
