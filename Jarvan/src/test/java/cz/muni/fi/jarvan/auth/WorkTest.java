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
     * Test of setFrom method, of class Work.
     */
    @Test
    public void testExcSetFrom() {
        System.out.println("Test 1: setFrom- not supported value");
        Work work = new Work();
        String from = "1899";
        try {
            work.setFrom(from);
            System.out.println("Error: exception caught");
            fail();
        } catch (CvException ex) {
        }
    }

    
    /**
     * Test of setFrom method, of class Work.
     */
    @Test
    public void testSetFrom() {
        System.out.println("Test 2: setFrom- supported value");
        Work work = new Work();
        String from = "2000";
        try {
            work.setFrom(from);
        } catch (CvException ex) {
            System.out.println("Error: exception caught");
            fail();
        }
    }
    
    
    /**
     * Test of setTo method, of class Work.
     */
    @Test
    public void testExcSetTo() throws Exception {
        System.out.println("Test 3: setTo- not supported value");
        Work instance = new Work();
        String to = "1899";
        try {
            instance.setTo(to);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
    * Test of setTo method, of class Work.
    */
    @Test
    public void testSetTo() {
        System.out.println("Test 4: setTo- supported value");
        Work work = new Work();
        String to = "2000";
        try {
            work.setTo(to);
        } catch (CvException ex) {
            System.out.println("Error: exception caught");
            fail();
        }
    }
}