/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;

import java.util.ArrayList;
import java.util.List;
import lt.viko.eif.finalproject.models.Log;
import lt.viko.eif.finalproject.models.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laimis
 */
public class LogDaoImplTest {

    /**
     * Test of getAll method, of class LogDaoImpl.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        LogDaoImpl instance = new LogDaoImpl();
        List<Log> result = instance.getAll();
        assertNotNull(result);
    }

    /**
     * Test of getById method, of class LogDaoImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        int id = 0;
        LogDaoImpl instance = new LogDaoImpl();
        Log expResult = null;
        Log result = instance.getById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of addLog method, of class LogDaoImpl.
     */
    @Test
    public void testAddLog() {
        System.out.println("addLog");
        UserDaoImpl userInstance = new UserDaoImpl();
        User user;
        user = userInstance.getById(5);
        Log log = new Log();
        log.setUser(user);
        log.setAddress("Kareiviu 11");
        log.setCity("Vilnius");
        log.setPlaceType("asd");
        log.setPlaceName("qwer");
        LogDaoImpl instance = new LogDaoImpl();
        Log expResult = log;
        Log result = instance.addLog(log);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteLog method, of class LogDaoImpl.
     */
    @Test
    public void testDeleteLog() {
        System.out.println("deleteLog");
        int id = 0;
        LogDaoImpl instance = new LogDaoImpl();
        boolean expResult = false;
        boolean result = instance.deleteLog(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserLogs method, of class LogDaoImpl.
     */
    @Test
    public void testGetUserLogs() {
        System.out.println("getUserLogs");
        int userid = 0;
        LogDaoImpl instance = new LogDaoImpl();
        List<Log> expResult = new ArrayList<>();
        List<Log> result = instance.getUserLogs(userid);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserLogById method, of class LogDaoImpl.
     */
    @Test
    public void testGetUserLogById() {
        System.out.println("getUserLogById");
        int userid = 0;
        int logid = 0;
        LogDaoImpl instance = new LogDaoImpl();
        Log expResult = null;
        Log result = instance.getUserLogById(userid, logid);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFilteredLogs method, of class LogDaoImpl.
     */
    @Test
    public void testGetFilteredLogs() {
        System.out.println("getFilteredLogs");
        String city = "";
        String address = "";
        String placeName = "";
        String placeType = "";
        LogDaoImpl instance = new LogDaoImpl();
        List<Log> expResult = new ArrayList<>();
        List<Log> result = instance.getFilteredLogs(city, address, placeName, placeType);
        assertEquals(expResult, result);
    }
    
}