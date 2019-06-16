/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;



/**
 * Dao factory class.
 * @author donatas
 */
public abstract class DaoFactory {
    
    /**
     * Method to get dao implementation
     * @return LogDao implementationn.
     */
    public abstract LogDao getLogDao();
    /**
     * Method to get dao implementation
     * @return UserDao implementationn.
     */
    public abstract UserDao getUserDao();
    
}
