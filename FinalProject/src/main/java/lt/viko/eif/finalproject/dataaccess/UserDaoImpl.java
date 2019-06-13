/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lt.viko.eif.finalproject.models.User;

/**
 *
 * @author donatas
 */
public class UserDaoImpl implements UserDao{

    
    
    @Override
    public List<User> getAll() {
        List <User> allUsers = new ArrayList<>();
        
        try {
            
            Connection connection = FinalProjectDatabase.createConnection();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category  FROM User");

            while (rs.next()){
                allUsers.add(new User(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4),
                        rs.getDouble(5), rs.getDouble(6), rs.getBigDecimal(7), rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }

    @Override
    public User getById(int id) {
        User user  = null;
        String query = "SELECT User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category  FROM User Where User.Id = ?";
        try {
            
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                user = new User(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4),
                        rs.getDouble(5), rs.getDouble(6), rs.getBigDecimal(7), rs.getString(8));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        String query = "INSERT INTO User (User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category)"
                + " values (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, user.getNick());
            preparedStmt.setDouble(2, user.getLat());
            preparedStmt.setDouble(3, user.getLng());
            preparedStmt.setDouble(4, user.getMass());
            preparedStmt.setDouble(5, user.getHeight());
            preparedStmt.setBigDecimal(6, user.getBmi());
            preparedStmt.setString(7, user.getCategory());
            int id = preparedStmt.executeUpdate();
            user.setId(id);
            connection.close();
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean deleteUser (int id) {
        String query = "Delete from User Where User.Id = ?";
        try {
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();

            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    @Override
    public boolean updateUser(int id, User user) {
        String query = "Update User set User.Nick = ?, User.Lat = ?, User.Lng = ?, User.Mass = ?, "
                + "User.Height = ?, User.BMI  = ?, User.Category = ? Where User.Id = ? ";
        try {
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(8, id);
            preparedStmt.setString(1, user.getNick());
            preparedStmt.setDouble(2, user.getLat());
            preparedStmt.setDouble(3, user.getLng());
            preparedStmt.setDouble(4, user.getMass());
            preparedStmt.setDouble(5, user.getHeight());
            preparedStmt.setBigDecimal(6, user.getBmi());
            preparedStmt.setString(7, user.getCategory());
            preparedStmt.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 
}
