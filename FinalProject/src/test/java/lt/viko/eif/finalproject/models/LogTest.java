/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.models;

import com.google.maps.model.OpeningHours;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laimis
 */
public class LogTest {
    
    public LogTest() {
    }

    /**
     * Test of setOpeningHours method, of class Log.
     */
    @Test
    public void testSetOpeningHours() {
        System.out.println("setOpeningHours");
        OpeningHours openingHours = null;
        Log instance = new Log();
        instance.setOpeningHours(openingHours);
    }

    /**
     * Test of getOpeningHours method, of class Log.
     */
    @Test
    public void testGetOpeningHours() {
        System.out.println("getOpeningHours");
        Log instance = new Log();
        OpeningHours expResult = null;
        OpeningHours result = instance.getOpeningHours();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Log.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Log instance = new Log();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Log.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = null;
        Log instance = new Log();
        instance.setId(id);
    }

    /**
     * Test of getCity method, of class Log.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        Log instance = new Log();
        String expResult = null;
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method, of class Log.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        String city = "";
        Log instance = new Log();
        instance.setCity(city);
    }

    /**
     * Test of getAddress method, of class Log.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Log instance = new Log();
        String expResult = null;
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Log.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "";
        Log instance = new Log();
        instance.setAddress(address);
    }

    /**
     * Test of getPlaceType method, of class Log.
     */
    @Test
    public void testGetPlaceType() {
        System.out.println("getPlaceType");
        Log instance = new Log();
        String expResult = null;
        String result = instance.getPlaceType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlaceType method, of class Log.
     */
    @Test
    public void testSetPlaceType() {
        System.out.println("setPlaceType");
        String placeType = "";
        Log instance = new Log();
        instance.setPlaceType(placeType);
    }

    /**
     * Test of getPlaceName method, of class Log.
     */
    @Test
    public void testGetPlaceName() {
        System.out.println("getPlaceName");
        Log instance = new Log();
        String expResult = null;
        String result = instance.getPlaceName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlaceName method, of class Log.
     */
    @Test
    public void testSetPlaceName() {
        System.out.println("setPlaceName");
        String placeName = "";
        Log instance = new Log();
        instance.setPlaceName(placeName);
    }

    /**
     * Test of getUser method, of class Log.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Log instance = new Log();
        User expResult = null;
        User result = instance.getUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUser method, of class Log.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        User user = null;
        Log instance = new Log();
        instance.setUser(user);
    }

    /**
     * Test of hashCode method, of class Log.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Log instance = new Log();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Log.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Log instance = new Log();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    
}
