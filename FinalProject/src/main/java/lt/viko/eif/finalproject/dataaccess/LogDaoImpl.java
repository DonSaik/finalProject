/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.dataaccess;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lt.viko.eif.finalproject.models.Log;
import lt.viko.eif.finalproject.models.User;


/**
 *
 * @author donatas
 */
public class LogDaoImpl implements LogDao{

    
    
    @Override
    public List<Log> getAll() {
        List <Log> allLogs = new ArrayList<>();
        
        try {
            
            Connection connection = FinalProjectDatabase.createConnection();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT Log.Id, Log.City, Log.Address, Log.PlaceName, Log.PlaceType, "
                    + "User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category "
                    + "FROM Log INNER JOIN User on User.Id = Log.UserID");

            while (rs.next()){
                allLogs.add(new Log(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        new User(rs.getInt(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),
                        rs.getDouble(10), rs.getDouble(11), rs.getBigDecimal(12), rs.getString(13))));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(LogDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allLogs;
    }

    @Override
    public Log getById(int id) {
        Log log  = null;
        String query = "SELECT Log.Id, Log.City, Log.Address, Log.PlaceName, Log.PlaceType, "
                    + "User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category "
                    + "FROM Log INNER JOIN User on User.Id = Log.UserID WHERE Log.Id = ?";
        try {
            
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                log = new Log(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        new User(rs.getInt(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),
                        rs.getDouble(10), rs.getDouble(11), rs.getBigDecimal(12), rs.getString(13)));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(LogDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return log;
        
    }

    @Override
    public Log addLog (Log log) {
        String query = "INSERT INTO Log (Log.UserID, Log.City, Log.Address, Log.PlaceName, Log.PlaceType)"
                + " values (?, ?, ?, ?, ?)";
        try {
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, log.getUser().getId());
            preparedStmt.setString(2, new String (log.getCity().getBytes(), "UTF-8"));
            preparedStmt.setString(3, new String (log.getAddress().getBytes(), "UTF-8"));
            preparedStmt.setString(4, new String (log.getPlaceName().getBytes(), "UTF-8"));
            preparedStmt.setString(5, new String (log.getPlaceType().getBytes(), "UTF-8"));
            preparedStmt.executeUpdate();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            int id = 0;
            if(rs.next()){
                id = rs.getInt(1);
            }
            log.setId(id);
            connection.close();
            return log;
        } catch (SQLException ex) {
            Logger.getLogger(LogDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LogDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean deleteLog(int id) {
        String query = "Delete from Recipe Where Recipe.Id = ?";
        try {
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();

            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LogDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    @Override
    public boolean updateLog(int id, Log log) {
        String query = "Update Recipe set Recipe.name= ? Where Recipe.Id = ? ";
        try {
            
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(2, id);
            preparedStmt.setString(1, log.getAddress());
            preparedStmt.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LogDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Log> getUserLogs(int userid) {
        List <Log> allLogs = new ArrayList<>();
        String query = "SELECT Log.Id, Log.City, Log.Address, Log.PlaceName, Log.PlaceType, "
                    + "User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category "
                    + "FROM Log INNER JOIN User on User.Id = Log.UserID "
                + "WHERE Log.UserId =?";
        try {
            
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, userid);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                allLogs.add(new Log(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        new User(rs.getInt(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),
                        rs.getDouble(10), rs.getDouble(11), rs.getBigDecimal(12), rs.getString(13))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allLogs;
    }

    @Override
    public Log getUserLogById(int userid, int logid) {
        Log log  = null;
        String query = "SELECT Log.Id, Log.City, Log.Address, Log.PlaceName, Log.PlaceType, "
                    + "User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category "
                    + "FROM Log INNER JOIN User on User.Id = Log.UserID"
                + " WHERE Log.UserId =? AND Log.Id = ?";
        try {
            
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, userid);
            stmt.setInt(2, logid);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                log =new Log(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        new User(rs.getInt(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),
                        rs.getDouble(10), rs.getDouble(11), rs.getBigDecimal(12), rs.getString(13)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LogDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return log;
    }

    @Override
    public List<Log> getFilteredLogs( String city, String address, String placeName, String placeType) {
        List <Log> allLogs = new ArrayList<>();
        String query = "SELECT Log.Id, Log.City, Log.Address, Log.PlaceName, Log.PlaceType, "
                    + "User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category "
                    + "FROM Log INNER JOIN User on User.Id = Log.UserID "
                + "WHERE (Log.City = ? OR ? IS NULL) "
                + "AND (Log.Address= ? OR ? IS NULL) "
                + "AND (Log.PlaceName= ? OR ? IS NULL)"
                + "AND (Log.PlaceType = ? OR ? IS NULL)";
        try {
            
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString (1, city);
            stmt.setString (2, city);
            stmt.setString (3, address);
            stmt.setString (4, address);
            stmt.setString (5, placeName);
            stmt.setString (6, placeName);
            stmt.setString (7, placeType);
            stmt.setString (8, placeType);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                allLogs.add(new Log(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        new User(rs.getInt(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),
                        rs.getDouble(10), rs.getDouble(11), rs.getBigDecimal(12), rs.getString(13))));
            }
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(LogDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allLogs;
    }
    
}
