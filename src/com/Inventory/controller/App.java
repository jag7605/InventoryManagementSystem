/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Inventory.controller;

/**
 *
 * @author lee71
 */
import com.Inventory.controller.MainController;

import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {
// Ensure GUI runs on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new MainController(new javax.swing.JFrame("Inventory Management System"));
        });
    }
}
