/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.models;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laimis
 */
public class UserTest {
    
    public UserTest() {
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        User instance = new User();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class User.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = null;
        User instance = new User();
        instance.setId(id);
    }

    /**
     * Test of getNick method, of class User.
     */
    @Test
    public void testGetNick() {
        System.out.println("getNick");
        User instance = new User();
        String expResult = null;
        String result = instance.getNick();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNick method, of class User.
     */
    @Test
    public void testSetNick() {
        System.out.println("setNick");
        String nick = "";
        User instance = new User();
        instance.setNick(nick);
    }

    /**
     * Test of getLat method, of class User.
     */
    @Test
    public void testGetLat() {
        System.out.println("getLat");
        User instance = new User();
        double expResult = 0.0;
        double result = instance.getLat();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLat method, of class User.
     */
    @Test
    public void testSetLat() {
        System.out.println("setLat");
        double lat = 0.0;
        User instance = new User();
        instance.setLat(lat);
    }

    /**
     * Test of getLng method, of class User.
     */
    @Test
    public void testGetLng() {
        System.out.println("getLng");
        User instance = new User();
        double expResult = 0.0;
        double result = instance.getLng();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLng method, of class User.
     */
    @Test
    public void testSetLng() {
        System.out.println("setLng");
        double lng = 0.0;
        User instance = new User();
        instance.setLng(lng);
    }

    /**
     * Test of getMass method, of class User.
     */
    @Test
    public void testGetMass() {
        System.out.println("getMass");
        User instance = new User();
        double expResult = 0.0;
        double result = instance.getMass();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setMass method, of class User.
     */
    @Test
    public void testSetMass() {
        System.out.println("setMass");
        double mass = 0.0;
        User instance = new User();
        instance.setMass(mass);
    }

    /**
     * Test of getHeight method, of class User.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        User instance = new User();
        double expResult = 0.0;
        double result = instance.getHeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setHeight method, of class User.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        double height = 0.0;
        User instance = new User();
        instance.setHeight(height);
    }

    /**
     * Test of getBmi method, of class User.
     */
    @Test
    public void testGetBmi() {
        System.out.println("getBmi");
        User instance = new User();
        BigDecimal expResult = null;
        BigDecimal result = instance.getBmi();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBmi method, of class User.
     */
    @Test
    public void testSetBmi() {
        System.out.println("setBmi");
        BigDecimal bmi = null;
        User instance = new User();
        instance.setBmi(bmi);
    }

    /**
     * Test of getCategory method, of class User.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        User instance = new User();
        String expResult = null;
        String result = instance.getCategory();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCategory method, of class User.
     */
    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        String category = "";
        User instance = new User();
        instance.setCategory(category);
    }

    /**
     * Test of getLogList method, of class User.
     */
    @Test
    public void testGetLogList() {
        System.out.println("getLogList");
        User instance = new User();
        List<Log> expResult = null;
        List<Log> result = instance.getLogList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogList method, of class User.
     */
    @Test
    public void testSetLogList() {
        System.out.println("setLogList");
        List<Log> logList = null;
        User instance = new User();
        instance.setLogList(logList);
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        User instance = new User();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    
}
