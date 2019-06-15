/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;

import java.math.BigDecimal;
import java.util.List;
import lt.viko.eif.finalproject.models.User;

/**
 *
 * @author donatas
 */
public interface UserDao {
    
    List<User> getAll();
    User getById(int id);
    User addUser(User user);
    boolean deleteUser(int id);
    boolean updateUser (int id, User user);
    List<User> getFilteredUsers(String category, double lat, double lng, double mass, double height, BigDecimal bmi);
}
