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
 * Log dao interface.
 * @author donatas
 */
public interface LogDao {
    
    /**
     * Gets all logs.
     * @return
     * @throws SQLException 
     */
    List<Log> getAll() throws SQLException;
    /**
     * Gets one log by id.
     * @return
     * @throws SQLException 
     */
    Log getById(int id)throws SQLException;
    /**
     * Adds one Log.
     * @param log
     * @return
     * @throws SQLException 
     */
    Log addLog(Log log)throws SQLException;
    /**
     * Deletes one log.
     * @param id
     * @return
     * @throws SQLException 
     */
    boolean deleteLog(int id)throws SQLException;
    /**
     * Updates log.
     * @param id
     * @param log
     * @return
     * @throws SQLException 
     */
    boolean updateLog(int id, Log log)throws SQLException;
    /**
     * Gets filtered logs.
     * @param city
     * @param address
     * @param placeName
     * @param placeType
     * @return
     * @throws SQLException 
     */
    List<Log> getFilteredLogs(String city, String address, String placeName, String placeType)throws SQLException;
    /**
     * Get specific user logs.
     * @param userid
     * @return
     * @throws SQLException 
     */
    List<Log> getUserLogs(int userid)throws SQLException;
    /**
     * Gets specific user log.
     * @param userid
     * @param logid
     * @return
     * @throws SQLException 
     */
    Log getUserLogById(int userid, int logid)throws SQLException;
}
