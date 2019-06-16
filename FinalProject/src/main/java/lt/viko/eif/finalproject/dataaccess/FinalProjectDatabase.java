/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author donatas
 */
public class FinalProjectDatabase extends DaoFactory {
    
    public static String connectionString= "jdbc:mysql://localhost:3306/finalProject?useLegacyDatetimeCode=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
    public static String userString = "root";
    public static String passString = "";
    
    public static Connection createConnection() throws SQLException{
        return DriverManager.getConnection(connectionString, userString, passString);
    }

    @Override
    public UserDao getUserDao() {
       return new UserDaoImpl();
    }

    @Override
    public LogDao getLogDao() {
        return new LogDaoImpl();
    }

    
    
}
