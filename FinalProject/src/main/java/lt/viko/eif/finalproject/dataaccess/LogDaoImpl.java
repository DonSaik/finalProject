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
import javax.ws.rs.NotFoundException;
import lt.viko.eif.finalproject.models.Log;
import lt.viko.eif.finalproject.models.User;


/**
 *
 * @author donatas
 */
public class LogDaoImpl implements LogDao{

    
    
    @Override
    public List<Log> getAll() throws SQLException {
        List <Log> allLogs = new ArrayList<>();
        
        
            
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
        
        if (allLogs.isEmpty()) throw new NotFoundException("No log found");
        return allLogs;
    }

    @Override
    public Log getById(int id) throws SQLException{
        Log log  = null;
        String query = "SELECT Log.Id, Log.City, Log.Address, Log.PlaceName, Log.PlaceType, "
                    + "User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category "
                    + "FROM Log INNER JOIN User on User.Id = Log.UserID WHERE Log.Id = ?";
        
            
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
       
        if (log == null) throw new NotFoundException("No log found");
        return log;
        
    }

    @Override
    public Log addLog (Log log) throws SQLException{
        String query = "INSERT INTO Log (Log.UserID, Log.City, Log.Address, Log.PlaceName, Log.PlaceType)"
                + " values (?, ?, ?, ?, ?)";
        
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, log.getUser().getId());
            preparedStmt.setString(2, log.getCity());
            preparedStmt.setString(3, log.getAddress());
            preparedStmt.setString(4, log.getPlaceName());
            preparedStmt.setString(5, log.getPlaceType());
            preparedStmt.executeUpdate();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            int id = 0;
            if(rs.next()){
                id = rs.getInt(1);
            }
            log.setId(id);
            connection.close();
            return log;
        
    }

    @Override
    public boolean deleteLog(int id)throws SQLException {
        String query = "Delete from Recipe Where Recipe.Id = ?";
        
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();

            connection.close();
            return true;
        
    }


    @Override
    public boolean updateLog(int id, Log log) throws SQLException {
        String query = "Update Recipe set Recipe.name= ? Where Recipe.Id = ? ";
        
            
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(2, id);
            preparedStmt.setString(1, log.getAddress());
            preparedStmt.executeUpdate();
            connection.close();
            return true;
    }

    @Override
    public List<Log> getUserLogs(int userid)throws SQLException {
        List <Log> allLogs = new ArrayList<>();
        String query = "SELECT Log.Id, Log.City, Log.Address, Log.PlaceName, Log.PlaceType, "
                    + "User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category "
                    + "FROM Log INNER JOIN User on User.Id = Log.UserID "
                + "WHERE Log.UserId =?";
        
            
            Connection connection = FinalProjectDatabase.createConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, userid);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                allLogs.add(new Log(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        new User(rs.getInt(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),
                        rs.getDouble(10), rs.getDouble(11), rs.getBigDecimal(12), rs.getString(13))));
            }
        
        if (allLogs.isEmpty()) throw new NotFoundException("No log found");
        return allLogs;
    }

    @Override
    public Log getUserLogById(int userid, int logid) throws SQLException{
        Log log  = null;
        String query = "SELECT Log.Id, Log.City, Log.Address, Log.PlaceName, Log.PlaceType, "
                    + "User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category "
                    + "FROM Log INNER JOIN User on User.Id = Log.UserID"
                + " WHERE Log.UserId =? AND Log.Id = ?";
        
            
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
            
        
        if (log ==null) throw new NotFoundException("No log found");
        return log;
    }

    @Override
    public List<Log> getFilteredLogs( String city, String address, String placeName, String placeType) throws SQLException{
        List <Log> allLogs = new ArrayList<>();
        String query = "SELECT Log.Id, Log.City, Log.Address, Log.PlaceName, Log.PlaceType, "
                    + "User.id, User.Nick, User.Lat, User.Lng, User.Mass, User.Height, User.BMI, User.Category "
                    + "FROM Log INNER JOIN User on User.Id = Log.UserID "
                + "WHERE (Log.City = ? OR ? IS NULL) "
                + "AND (Log.Address= ? OR ? IS NULL) "
                + "AND (Log.PlaceName= ? OR ? IS NULL)"
                + "AND (Log.PlaceType = ? OR ? IS NULL)";
        
            
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
            
        
        if (allLogs.isEmpty()) throw new NotFoundException("No logs found");
        return allLogs;
    }
    
}
