/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.jarvan.auth;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of methods, of class Registration
 * @author joe
 */
public class RegistrationTest {
    
    
    @Test
    public void testUserExist()
    {
        System.out.println("Test 1: User already exists");
        Registration reg = new Registration("zajac", "testval", "testval");
        boolean exResult = false;
        boolean result = reg.tryRegister();
        
        assertEquals("username zajac", exResult, result);
        
    }
    
    
    @Test
    public void testEmailExist()
    {
        System.out.println("Test 2: Email already exists");
        Registration reg = new Registration("testval", "zajac@zajac.sk", "testval");
        boolean exResult = false;
        boolean result = reg.tryRegister();
        
        assertEquals("email zajac@zajac.sk", exResult, result);
        
    }
    
    @Test
    public void testNotExist()
    {
        System.out.println("Test 3: None existing values");
        XMLWriter userXml = new XMLWriter(Settings.getPathUser());
        Registration reg = new Registration("testval", "testval", "testval");
        boolean exResult = true;
        boolean result = reg.tryRegister();
        userXml.deleteUser("testval");
        assertEquals("none existing", exResult, result);    
    }

}