/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lt.viko.eif.finalproject.models.User;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;


/**
 *
 * @author laimis
 */
public class UserDaoImplTest {

    
    /**
     * Test of getAll method, of class UserDaoImpl.
     */
    @Test
    public void testGetAll() throws SQLException{
        System.out.println("getAll");
        UserDaoImpl instance = new UserDaoImpl();
        List<User> result = instance.getAll();
        assertNotNull(result);
    }

    /**
     * Test of getById method, of class UserDaoImpl.
     */
    @Test
    public void testGetById() throws SQLException {
        System.out.println("getById");
        int id = 1;
        UserDaoImpl instance = new UserDaoImpl();
        User result = instance.getById(id);
        assertNotNull(result);
    }
    /**
     * Test of deleteUser method, of class UserDaoImpl.
     */
    @Test
    public void testDeleteUser() throws SQLException {
        System.out.println("deleteUser");
        int id = 0;
        UserDaoImpl instance = new UserDaoImpl();
        boolean expResult = true;
        boolean result = instance.deleteUser(id);
        assertEquals(expResult, result);

    }

    /**
     * Test of getFilteredUsers method, of class UserDaoImpl.
     */
    @Ignore
    @Test
    public void testGetFilteredUsers() throws SQLException {
        System.out.println("getFilteredUsers");
        String category = "";
        double lat = 54;
        double lng = 24;
        double mass = 75;
        double height = 1.8;
        BigDecimal bmi = new BigDecimal(25);
        UserDaoImpl instance = new UserDaoImpl();
        List<User> expResult = new ArrayList();
        List<User> result = instance.getFilteredUsers(category, lat, lng, mass, height, bmi);
        assertEquals(expResult, result);
    }
    
}
