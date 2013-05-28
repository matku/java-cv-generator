package cz.muni.fi.jarvan.auth;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of methods, of class Work
 * @author joe
 */
public class WorkTest {
    

    /**
     * Test of setFrom method, of class Work
     */
    @Test
    public void testExSetFrom() {
        System.out.println("Test 1: setFrom -not supported value 1");
        Work work = new Work();
        String from = "1899";
        try {
            work.setFrom(from);
            fail("Error: exception not thrown");
        } catch (CvException ex) {
        }
    }

 
    /**
     * Test of setFrom method, of class Work
     */
    @Test
    public void testExSetFrom1() {
        System.out.println("Test 2: setFrom -not supported value 2");
        Work work = new Work();
        String from = "2101";
        try {
            work.setFrom(from);
            fail("Error: exception not thrown");
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setFrom method, of class Work.
     */
    @Test
    public void testSetFrom2() {
        System.out.println("Test 3: setFrom -supported value");
        Work work = new Work();
        String from = "2000";
        try {
            work.setFrom(from);
        } catch (CvException ex) {
            fail("Error: exception thrown");
        }
    }
    
    
    /**
     * Test of setTo method, of class Work.
     */
    @Test
    public void testExSetTo() {
        System.out.println("Test 4: setTo -not supported value 1");
        Work instance = new Work();
        String to = "1899";
        try {
            instance.setTo(to);
            fail("Error: exception not thrown");
        } catch (CvException ex) {
        }
    }
    
    
    
        /**
     * Test of setTo method, of class Work.
     */
    @Test
    public void testExSetTo1() {
        System.out.println("Test 5: setTo -not supported value 2");
        Work instance = new Work();
        String to = "2101";
        try {
            instance.setTo(to);
            fail("Error: exception not thrown");
        } catch (CvException ex) {
        }
    }
    
    
    /**
    * Test of setTo method, of class Work.
    */
    @Test
    public void testSetTo2() {
        System.out.println("Test 6: setTo- supported value");
        Work work = new Work();
        String to = "2000";
        try {
            work.setTo(to);
        } catch (CvException ex) {
            fail("Error: exception thrown");
        }
    }
}