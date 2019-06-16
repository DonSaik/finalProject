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
import javax.ws.rs.NotFoundException;
import lt.viko.eif.finalproject.models.User;

/**
 *
 * @author donatas
 */
public class UserDaoImpl implements UserDao{

    
    
    @Override
    public List<User> getAll() throws SQLException {
        List <User> allUsers = new ArrayList<>();
        
            
            Connection connection = FinalProjectDatabase.createConnection();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category  FROM User");

            while (rs.next()){
                allUsers.add(new User(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4),
                        rs.getDouble(5), rs.getDouble(6), rs.getBigDecimal(7), rs.getString(8)));
            }
            connection.close();
       
         if (allUsers.isEmpty()) throw new NotFoundException("No user found");
        return allUsers;
    }

    @Override
    public User getById(int id)throws SQLException {
        User user  = null;
        String query = "SELECT User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category  FROM User Where User.Id = ?";
        
            
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                user = new User(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4),
                        rs.getDouble(5), rs.getDouble(6), rs.getBigDecimal(7), rs.getString(8));
            }
            connection.close();
        
        if (user == null) throw new NotFoundException("No user found");
        return user;
    }

    @Override
    public User addUser(User user) throws SQLException{
        String query = "INSERT INTO User (User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category)"
                + " values (?, ?, ?, ?, ?, ?, ?)";
        
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, user.getNick());
            preparedStmt.setDouble(2, user.getLat());
            preparedStmt.setDouble(3, user.getLng());
            preparedStmt.setDouble(4, user.getMass());
            preparedStmt.setDouble(5, user.getHeight());
            preparedStmt.setBigDecimal(6, user.getBmi());
            preparedStmt.setString(7, user.getCategory());
            preparedStmt.executeUpdate();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            int id = 0;
            if(rs.next()){
                id = rs.getInt(1);
            }
            user.setId(id);
            connection.close();
            return user;
        
    }

    @Override
    public boolean deleteUser (int id)throws SQLException {
        String query = "Delete from User Where User.Id = ?";
        
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();

            connection.close();
            return true;
    }


    @Override
    public boolean updateUser(int id, User user) throws SQLException{
        String query = "Update User set User.Nick = ?, User.Lat = ?, User.Lng = ?, User.Mass = ?, "
                + "User.Height = ?, User.BMI  = ?, User.Category = ? Where User.Id = ? ";
      
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
    } 

    @Override
    public List<User> getFilteredUsers(String category, double lat, double lng, double mass, double height, BigDecimal bmi) throws SQLException {
        List <User> allUsers = new ArrayList<>();
        String query = "SELECT User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category  FROM User "
                + "Where (User.Category = ? OR ? IS NULL) "
                + "AND (User.Lat= ? OR ? > 90 OR ? < -90) "
                + "AND (User.Lng= ? OR ? > 180 OR ? < -180) "
                + "AND (User.Mass = ? OR ? = 0)"
                + "AND (User.Height = ? OR ? = 0)"
                + "AND (User.bmi = ? OR ? = 0)";
        
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, category);
            stmt.setString(2, category);
            stmt.setDouble(3, lat);
            stmt.setDouble(4, lat);
            stmt.setDouble(5, lat);
            stmt.setDouble(6, lng);
            stmt.setDouble(7, lng);
            stmt.setDouble(8, lng);
            stmt.setDouble(9, mass);
            stmt.setDouble(10, mass);
            stmt.setDouble(11, height);
            stmt.setDouble(12, height);
            stmt.setBigDecimal(13, bmi);
            stmt.setBigDecimal(14, bmi);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                allUsers.add(new User(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4),
                        rs.getDouble(5), rs.getDouble(6), rs.getBigDecimal(7), rs.getString(8)));
            }
            connection.close();
        
        if (allUsers.isEmpty()) throw new NotFoundException("No user found");
        return allUsers;
    }
}
