/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;



/**
 *
 * @author donatas
 */
public abstract class DaoFactory {
    
    public abstract LogDao getLogDao();
    public abstract UserDao getUserDao();
    
}
