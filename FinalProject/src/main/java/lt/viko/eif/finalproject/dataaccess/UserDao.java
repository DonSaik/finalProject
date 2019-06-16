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
 *
 * @author donatas
 */
public interface UserDao {
    
    List<User> getAll()throws SQLException;
    User getById(int id)throws SQLException;
    User addUser(User user)throws SQLException;
    boolean deleteUser(int id)throws SQLException;
    boolean updateUser (int id, User user)throws SQLException;
    List<User> getFilteredUsers(String category, double lat, double lng, double mass, double height, BigDecimal bmi)throws SQLException;
}
