/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laimis
 */
public class FinalProjectDatabaseTest {

    /**
     * Test of createConnection method, of class FinalProjectDatabase.
     */
    @Test
    public void testCreateConnection() {
        System.out.println("createConnection");
        Connection result = FinalProjectDatabase.createConnection();
        assertNotNull(result);

    }

    /**
     * Test of getUserDao method, of class FinalProjectDatabase.
     */
    @Test
    public void testGetUserDao() {
        System.out.println("getUserDao");
        FinalProjectDatabase instance = new FinalProjectDatabase();
        UserDao result = instance.getUserDao();
        assertNotNull(result);

    }

    /**
     * Test of getLogDao method, of class FinalProjectDatabase.
     */
    @Test
    public void testGetLogDao() {
        System.out.println("getLogDao");
        FinalProjectDatabase instance = new FinalProjectDatabase();
        LogDao result = instance.getLogDao();
        assertNotNull(result);

    }
    
}
