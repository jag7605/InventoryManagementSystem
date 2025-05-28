/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.controller;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
/**
 *
 * @author jagrithnarayan
 */
// testing
public class PanelManager 
{
    private final CardLayout layout;
    private final JPanel container;
    private final HashMap<String, JPanel> panelMap;
    
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
        panelMap.put(name, panel);
        container.add(panel, name);
    }
    
    public void showPanel(String name)
    {
        layout.show(container,name);
    }
    
    public JPanel getPanel(String name)
    {
        return panelMap.get(name);
    }
}
