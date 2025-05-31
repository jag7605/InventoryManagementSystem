/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.controller;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
/**
 * PanelManager is responsible for managing the switching between different GUI panels
 * using CardLayout. 
 * @author lee71
 */

public class PanelManager 
{
    private final CardLayout layout;
    private final JPanel container;
    private final HashMap<String, JPanel> panelMap;
    
    
    // constructor: Initialize the layout and main container, 
    // sets up JFrame to use this container.
    public PanelManager(JFrame frame)
    {
        this.layout = new CardLayout();
        this.container = new JPanel(layout);
        this.panelMap = new HashMap<>();
        
        frame.setContentPane(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }
    
    public void addPanel(String name, JPanel panel)
    {
        panelMap.put(name, panel);  // store the panel in the map
        container.add(panel, name);  // add the panel to the CardLayout container
    }
    
    // display the panel associated with the given name
    public void showPanel(String name)
    {
        layout.show(container,name);
    }
    
    // retrives the panel associated with the given name
    public JPanel getPanel(String name)
    {
        return panelMap.get(name);
    }
}
