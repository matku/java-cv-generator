package cz.muni.fi.jarvan.auth;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of setFrom method, of class Cv
 * @author joe
 */
public class CvTest {
    

    /**
     * Test of setFirstName method, of class Cv.
     */
    @Test
    public void testExSetFirstName() {
        System.out.println("Test 1: setFirstName -not supported vallue 1");
        String firstName = "testval";
        Cv instance = new Cv();
        try {
            instance.setFirstName(firstName);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    
    /**
     * Test of setFirstName method, of class Cv.
     */
    @Test
    public void testExSetFirstName1() {
        System.out.println("Test 2: setFirstName -not supported vallue 2");
        String firstName = "t3stval";
        Cv instance = new Cv();
        try {
            instance.setFirstName(firstName);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    
    /**
     * Test of setFirstName method, of class Cv.
     */
    @Test
    public void testExSetFirstName2() {
        System.out.println("Test 3: setFirstName -not supported vallue 3");
        String firstName = "TEstval";
        Cv instance = new Cv();
        try {
            instance.setFirstName(firstName);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setFirstName method, of class Cv.
     */
    @Test
    public void testExSetFirstName3() {
        System.out.println("Test 4: setFirstName- not supported vallue 4");
        String firstName = "T3stval";
        Cv instance = new Cv();
        try {
            instance.setFirstName(firstName);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setFirstName method, of class Cv.
     */
    @Test
    public void testSetFirstName4() {
        System.out.println("Test 5: setFirstName -supported vallue");
        String firstName = "Testval";
        Cv instance = new Cv();
        try {
            instance.setFirstName(firstName);
        } catch (CvException ex) {
            System.out.println("Error: exception caught");
            fail();
        }
    }



    /**
     * Test of setLastName method, of class Cv.
     */
    @Test
    public void testExcSetLastName() {
        System.out.println("Test 6: setLastName -not supported value 1");
        String lastName = "testval";
        Cv instance = new Cv();
        try {
            instance.setLastName(lastName);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    
        /**
     * Test of setLastName method, of class Cv.
     */
    @Test
    public void testExcSetLastName1() {
        System.out.println("Test 7: setLastName -not supported value 2");
        String lastName = "t3stval";
        Cv instance = new Cv();
        try {
            instance.setLastName(lastName);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }

    
    
    /**
     * Test of setLastName method, of class Cv.
     */
    @Test
    public void testExcSetLastName2() {
        System.out.println("Test 8: setLastName -not supported value 3");
        String lastName = "TEstval";
        Cv instance = new Cv();
        try {
            instance.setLastName(lastName);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    
    
    /**
     * Test of setLastName method, of class Cv.
     */
    @Test
    public void testExcSetLastName3() {
        System.out.println("Test 9: setLastName -not supported value 4");
        String lastName = "T3stval";
        Cv instance = new Cv();
        try {
            instance.setLastName(lastName);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    
    /**
     * Test of setLastName method, of class Cv.
     */
    @Test
    public void testSetLastName4() {
        System.out.println("Test 10: setLastName -supported value");
        String lastName = "Testval";
        Cv instance = new Cv();
        try {
            instance.setLastName(lastName);
        } catch (CvException ex) {
            System.out.println("Error: exception not caught");
            fail();
        }
    }


    /**
     * Test of setDateOfBirth method, of class Cv.
     */
    @Test
    public void testSetDateOfBirth() {
        System.out.println("Test 11: setDateOfBirth -not supported value 1");
        String dateOfBirth = "0.3.2000";
        Cv instance = new Cv();
        try {
            instance.setDateOfBirth(dateOfBirth);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }

   

    
    
    /**
     * Test of setDateOfBirth method, of class Cv.
     */
    @Test
    public void testSetDateOfBirth1() {
        System.out.println("Test 12: setDateOfBirth -not supported value 2");
        String dateOfBirth = "32.3.2000";
        Cv instance = new Cv();
        try {
            instance.setDateOfBirth(dateOfBirth);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    
    /**
     * Test of setDateOfBirth method, of class Cv.
     */
    @Test
    public void testSetDateOfBirth2() {
        System.out.println("Test 13: setDateOfBirth -not supported value 3");
        String dateOfBirth = "20.0.2000";
        Cv instance = new Cv();
        try {
            instance.setDateOfBirth(dateOfBirth);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setDateOfBirth method, of class Cv.
     */
    @Test
    public void testSetDateOfBirth3() {
        System.out.println("Test 14: setDateOfBirth -not supported value 4");
        String dateOfBirth = "20.13.2000";
        Cv instance = new Cv();
        try {
            instance.setDateOfBirth(dateOfBirth);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setDateOfBirth method, of class Cv.
     */
    @Test
    public void testSetDateOfBirth4() {
        System.out.println("Test 15: setDateOfBirth -not supported value 5");
        String dateOfBirth = "20.3.1899";
        Cv instance = new Cv();
        try {
            instance.setDateOfBirth(dateOfBirth);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setDateOfBirth method, of class Cv.
     */
    @Test
    public void testSetDateOfBirth5() {
        System.out.println("Test 16: setDateOfBirth -not supported value 6");
        String dateOfBirth = "20.3.2101";
        Cv instance = new Cv();
        try {
            instance.setDateOfBirth(dateOfBirth);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setDateOfBirth method, of class Cv.
     */
    @Test
    public void testSetDateOfBirth6() {
        System.out.println("Test 17: setDateOfBirth -supported value");
        String dateOfBirth = "20.3.2000";
        Cv instance = new Cv();
        try {
            instance.setDateOfBirth(dateOfBirth);
        } catch (CvException ex) {
            System.out.println("Error: exception caught");
            fail();
        }
    }
    
    
    /**
     * Test of setHomeNumber method, of class Cv.
     */
    @Test
    public void testSetHomeNumber() {
        System.out.println("Test 18: setHomeNumber -not supported value");
        String homeNumber = "testval";
        Cv instance = new Cv();
        try {
            instance.setHomeNumber(homeNumber);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }

   
    
    /**
     * Test of setHomeNumber method, of class Cv.
     */
    @Test
    public void testSetHomeNumber1() {
        System.out.println("Test 19: setHomeNumber -supported value");
        String homeNumber = "123456";
        Cv instance = new Cv();
        try {
            instance.setHomeNumber(homeNumber);
        } catch (CvException ex) {
            System.out.println("Error: exception caught");
            fail();
        }
    }
    
    
    /**
     * Test of setMobileNumber method, of class Cv.
     */
    @Test
    public void testSetMobileNumber() {
        System.out.println("Test 20: setMobileNumber -not supported value");
        String mobileNumber = "testval";
        Cv instance = new Cv();
        try {
            instance.setMobileNumber(mobileNumber);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }

   
    /**
     * Test of setMobileNumber method, of class Cv.
     */
    @Test
    public void testSetMobileNumber1() {
        System.out.println("Test 21: setMobileNumber -supported value");
        String mobileNumber = "123456";
        Cv instance = new Cv();
        try {
            instance.setMobileNumber(mobileNumber);
        } catch (CvException ex) {
            System.out.println("Error: exception caught");
            fail();
        }
    }
    
    
    /**
     * Test of setStreet method, of class Cv.
     */
    @Test
    public void testSetStreet() {
        System.out.println("Test 22: setStreet -not supported value 1");
        String street = "testval 3";
        Cv instance = new Cv();
        try {
            instance.setStreet(street);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }


    /**
     * Test of setStreet method, of class Cv.
     */
    @Test
    public void testSetStreet1() {
        System.out.println("Test 23: setStreet -not supported value 2");
        String street = "t3stval 3";
        Cv instance = new Cv();
        try {
            instance.setStreet(street);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setStreet method, of class Cv.
     */
    @Test
    public void testSetStreet2() {
        System.out.println("Test 24: setStreet -not supported value 3");
        String street = "TEstval 3";
        Cv instance = new Cv();
        try {
            instance.setStreet(street);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setStreet method, of class Cv.
     */
    @Test
    public void testSetStreet3() {
        System.out.println("Test 25: setStreet -not supported value 4");
        String street = "T3stval 3";
        Cv instance = new Cv();
        try {
            instance.setStreet(street);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setStreet method, of class Cv.
     */
    @Test
    public void testSetStreet4() {
        System.out.println("Test 26: setStreet -not supported value 5");
        String street = "Testval t ";
        Cv instance = new Cv();
        try {
            instance.setStreet(street);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setStreet method, of class Cv.
     */
    @Test
    public void testSetStreet5() {
        System.out.println("Test 27: setStreet -supported value");
        String street = "Testval 3";
        Cv instance = new Cv();
        try {
            instance.setStreet(street);
        } catch (CvException ex) {
            System.out.println("Error: exception caught");
            fail();
        }
    }
    
    
    /**
     * Test of setCity method, of class Cv.
     */
    @Test
    public void testSetCity() {
        System.out.println("Test 28: setCity -not supported value 1");
        String city = "testval";
        Cv instance = new Cv();
        try {
            instance.setCity(city);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }

    
    /**
     * Test of setCity method, of class Cv.
     */
    @Test
    public void testSetCity1() {
        System.out.println("Test 29: setCity -not supported value 2");
        String city = "t3stval";
        Cv instance = new Cv();
        try {
            instance.setCity(city);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setCity method, of class Cv.
     */
    @Test
    public void testSetCity2() {
        System.out.println("Test 30: setCity -not supported value 3");
        String city = "TEstval";
        Cv instance = new Cv();
        try {
            instance.setCity(city);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setCity method, of class Cv.
     */
    @Test
    public void testSetCity3() {
        System.out.println("Test 31: setCity -not supported value 4");
        String city = "T3stval";
        Cv instance = new Cv();
        try {
            instance.setCity(city);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setCity method, of class Cv.
     */
    @Test
    public void testSetCity4() {
        System.out.println("Test 32: setCity -supported value");
        String city = "Testval";
        Cv instance = new Cv();
        try {
            instance.setCity(city);
        } catch (CvException ex) {
            System.out.println("Error: exception caught");
            fail();
        }
    }
    
    
    /**
     * Test of setZip method, of class Cv.
     */
    @Test
    public void testSetZip() {
        System.out.println("Test 33: setZip -not supported value 1");
        String zip = "1234";
        Cv instance = new Cv();
        try {
            instance.setZip(zip);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setZip method, of class Cv.
     */
    @Test
    public void testSetZip1() {
        System.out.println("Test 34: setZip -not supported value 2");
        String zip = "123456";
        Cv instance = new Cv();
        try {
            instance.setZip(zip);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setZip method, of class Cv.
     */
    @Test
    public void testSetZip2() {
        System.out.println("Test 35: setZip -not supported value 3");
        String zip = "12 345";
        Cv instance = new Cv();
        try {
            instance.setZip(zip);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setZip method, of class Cv.
     */
    @Test
    public void testSetZip3() {
        System.out.println("Test 36: setZip -not supported value 4");
        String zip = "testval";
        Cv instance = new Cv();
        try {
            instance.setZip(zip);
            System.out.println("Error: exception not caught");
            fail();
        } catch (CvException ex) {
        }
    }
    
    
    /**
     * Test of setZip method, of class Cv.
     */
    @Test
    public void testSetZip4() {
        System.out.println("Test 37: setZip -supported value 1");
        String zip = "123 45";
        Cv instance = new Cv();
        try {
            instance.setZip(zip);
        } catch (CvException ex) {
            System.out.println("Error: exception caught");
            fail();
        }
    }
    
    
    /**
     * Test of setZip method, of class Cv.
     */
    @Test
    public void testSetZip5() {
        System.out.println("Test 38: setZip -supported value 2");
        String zip = "12345";
        Cv instance = new Cv();
        try {
            instance.setZip(zip);
        } catch (CvException ex) {
            System.out.println("Error: exception caught");
            fail();
        }
    }
    
    
    /**
     * Test of setStreet method, of class Cv.
     */
    @Test
    public void testSetStreet6() {
        System.out.println("Test 39: setStreet -not supported value 2");
        String street = "Testval t";
        Cv instance = new Cv();
        try {
            instance.setStreet(street);
        } catch (CvException ex) {
            System.out.println("Error: exception caught");
            fail();
        }
    }
}