package cz.muni.fi.jarvan.auth;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of methods, of class Education
 * @author joe
 */
public class EducationTest {
    

    /**
     * Test of setFrom method, of class Education.
     */
    @Test
    public void testExSetFrom() {
        System.out.println("Test 1: setFrom -not supported value");
        Work work = new Work();
        String from = "1899";
        try {
            work.setFrom(from);
            System.out.println("Error: exception not caught");
            fail();
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
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }

    
    /**
     * Test of setFrom method, of class Education
     */
    @Test
    public void testSetFrom2() {
        System.out.println("Test 3: setFrom -supported value");
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
     * Test of setTo method, of class Education.
     */
    @Test
    public void testExSetTo3() throws Exception {
        System.out.println("Test 4: setTo -not supported value");
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
    public void testExSetTo4() {
        System.out.println("Test 5: setTo -not supported value 2");
        Work instance = new Work();
        String to = "2101";
        try {
            instance.setTo(to);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
    * Test of setTo method, of class Education.
    */
    @Test
    public void testSetTo5() {
        System.out.println("Test 6: setTo -supported value");
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