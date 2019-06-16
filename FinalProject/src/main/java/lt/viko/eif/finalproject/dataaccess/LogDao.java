/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;

import java.sql.SQLException;
import java.util.List;
import lt.viko.eif.finalproject.models.Log;

/**
 *
 * @author donatas
 */
public interface LogDao {
    
    List<Log> getAll() throws SQLException;
    Log getById(int id)throws SQLException;
    Log addLog(Log log)throws SQLException;
    List<Log> getFilteredLogs(String city, String address, String placeName, String placeType)throws SQLException;
    List<Log> getUserLogs(int userid)throws SQLException;
    Log getUserLogById(int userid, int logid)throws SQLException;
}
