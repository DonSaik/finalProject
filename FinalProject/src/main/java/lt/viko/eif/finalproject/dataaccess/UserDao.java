/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import lt.viko.eif.finalproject.models.User;

/**
 * Interface for User dao.
 * @author donatas
 */
public interface UserDao {
    
    /**
     * Gets all users.
     * @return
     * @throws SQLException 
     */
    List<User> getAll()throws SQLException;
    /**
     * Gets one user.
     * @param id
     * @return
     * @throws SQLException 
     */
    User getById(int id)throws SQLException;
    /**
     * Adds one user.
     * @param user
     * @return
     * @throws SQLException 
     */
    User addUser(User user)throws SQLException;
    /**
     * Deletes user.
     * @param id
     * @return
     * @throws SQLException 
     */
    boolean deleteUser(int id)throws SQLException;
    /**
     * Updates user.
     * @param id
     * @param user
     * @return
     * @throws SQLException 
     */
    boolean updateUser (int id, User user)throws SQLException;
    /**
     * Gets filtered users.
     * @param category
     * @param lat
     * @param lng
     * @param mass
     * @param height
     * @param bmi
     * @return
     * @throws SQLException 
     */
    List<User> getFilteredUsers(String category, double lat, double lng, double mass, double height, BigDecimal bmi)throws SQLException;
}
