/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.Inventory.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jagrithnarayan
 */
public class UserManagerTest {

    // testing the AdminPassword
    @Test
    public void testPermit_adminPassword() {
        String result = UserManager.permit("AdminPassword");
        assertEquals("admin", result);
    }

    // testing the StaffPassword
    @Test
    public void testPermit_staffPassword() {
        String result = UserManager.permit("StaffPassword");
        assertEquals("staff", result);
    }

    // testing invalid password
    @Test
    public void testPermit_invalidPassword() {
        String result = UserManager.permit("WrongPassword");
        assertNull(result);
    }

    // testing empty password
    @Test
    public void testPermit_emptyPassword() {
        String result = UserManager.permit("");
        assertNull(result);
    }

    // testing for case sensitivity
    @Test
    public void testPermit_caseSensitivity() {
        String result = UserManager.permit("adminpassword"); // lowercase
        assertNull(result);
    }
}
