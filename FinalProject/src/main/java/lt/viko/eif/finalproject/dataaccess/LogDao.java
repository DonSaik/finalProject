/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;

import java.util.List;
import lt.viko.eif.finalproject.models.Log;

/**
 *
 * @author donatas
 */
public interface LogDao {
    
    List<Log> getAll();
    Log getById(int id);
    Log addLog(Log log);
    boolean deleteLog(int id);
    boolean updateLog(int id, Log log);   
}
