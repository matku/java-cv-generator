package cz.muni.fi.jarvan.auth;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of methods, of class User
 * @author joe
 */
public class UserTest {
    
    
    /**
     * Test of userAlreadyExists method, of class User.
     */
    @Test
    public void testUserAlreadyExists() {
        System.out.println("Test 1: userAlreadyExists -username exists");
        User user = new User();
        user.setUsername("zajac");
        boolean expResult = true;
        boolean result = user.userAlreadyExists();
        assertEquals("username zajac",expResult, result);
    }
    
    
    /**
     * Test of userAlreadyExists method, of class User.
     */
    @Test
    public void testUserNotExists() {
        System.out.println("Test 2: userAlreadyExists -username not exists");
        User user = new User();
        user.setUsername("testval");
        boolean expResult = false;
        boolean result = user.userAlreadyExists();
        assertEquals("username testval",expResult, result);
    }

    
    /**
     * Test of emailAlreadyExists method, of class User.
     */
    @Test
    public void testEmailAlreadyExists() {
        System.out.println("Test 3: emailAlreadyExists -email exists");
        User user = new User();
        user.setEmail("zajac@zajac.sk");
        boolean expResult = true;
        boolean result = user.emailAlreadyExists();
        assertEquals("email zajac@zajac.sk",expResult, result);
    }

    /**
     * Test of emailAlreadyExists method, of class User.
     */
    @Test
    public void testEmailNotExists() {
        System.out.println("Test 4: emailAlreadyExists -email not exists");
        User user = new User();
        user.setEmail("testval");
        boolean expResult = false;
        boolean result = user.emailAlreadyExists();
        assertEquals("email testval",expResult, result);
    
    
    }
       
    /**
     * Test of md5 method, of class User.
     */
    @Test
    public void testMd5() {
        System.out.println("Test 5: md5 -hash algorithm test");
        String input = "testval";
        String expResult = "48ea7adc1abf301837f93f0ec24b3072";
        String result = User.md5(input);
        assertEquals("password testval",expResult, result);
    }
}