/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.InventoryManagementSystemCLI;

/**
 *
 * @author Jin An Lee
 */
// This class is used to manage user permissions. The function will check if the password entered is either the staff or admin password
// and will return the matching user role.
public class UserManager {

    // String variables to hold the admin and staff passwords
    private static final String adminPassword = "AdminPassword";
    private static final String staffPassword = "StaffPassword";

    public static String Permit(String password) {
        if (password.equals(adminPassword)) {
            return "admin";
        } else if (password.equals(staffPassword)) {
            return "staff";
        } else {
            return null;
        }
    } 
}
