/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.resources;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lt.viko.eif.finalproject.dataaccess.FinalProjectDatabase;
import lt.viko.eif.finalproject.dataaccess.LogDao;
import lt.viko.eif.finalproject.models.Log;
import lt.viko.eif.finalproject.models.User;

/**
 * REST Web Service
 *
 * @author donatas
 */
@Path("logs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LogsResource {

    @Context
    private UriInfo context;
    
    private LogDao logDao = new FinalProjectDatabase().getLogDao();
    /**
     * Creates a new instance of LogsResource
     */
    public LogsResource() {
    }

    /**
     * Retrieves representation of an instance of lt.viko.eif.finalproject.resources.LogsResource
     * @return an instance of java.lang.String
     */
    @GET
    public Response getJson() {
        List<Log> logs =null;
        try{
        logs = logDao.getAll();}
        catch (NotFoundException ex){
            return Response.status(204).entity(logs).build();
        } catch (SQLException ex) {
             return Response.status(404).entity("Error: " +ex.getMessage()).build();
        }
        List<Link> links;
        for (Log log: logs){
            links = new ArrayList<>();
            links.add(new Link(getUriForUserLog(log), "self"));
            links.add(new Link(getUriForSelf(log.getUser()), "user"));
            log.setLinks(links);
            log.setUser(null);
        }
         return Response.status(200).entity(logs).build();
    }
    @GET
    @Path("/{id}")
    public Response getLogById(@PathParam("id") int id) throws Exception {
        Log log = null;
        try{
        log = logDao.getById(id);
        }
        catch (NotFoundException ex){
            return Response.status(204).entity(log).build();
        }
        catch (SQLException ex) {
             return Response.status(404).entity("Error: " +ex.getMessage()).build();
        }
        List<Link> links;
            links = new ArrayList<>();
            links.add(new Link(getUriForUserLog(log), "self"));
            links.add(new Link(getUriForSelf(log.getUser()), "user"));
            log.setLinks(links);
            log.setUser(null);
        return Response.status(200).entity(log).build();
    }
    @GET
    @Path("/q")
    public Response get(@QueryParam("city") String city, 
            @QueryParam("address") String address, 
            @QueryParam("placeName") String placeName, 
            @QueryParam("placeType") String placeType) throws Exception {
        System.out.println("Fetching user info by "+ city +" "+ address+" "+ placeName+" "+ placeType);
        List<Log> logs =null;
        try{
        logs = logDao.getFilteredLogs(city, address, placeName, placeType);
        }
        catch (NotFoundException ex){
            return Response.status(204).entity(logs).build();
        }
        catch (SQLException ex) {
             return Response.status(404).entity("Error: " +ex.getMessage()).build();
        }
        
        List<Link> links;
        for (Log log: logs){
            links = new ArrayList<>();
            links.add(new Link(getUriForUserLog(log), "self"));
            links.add(new Link(getUriForSelf(log.getUser()), "user"));
            log.setLinks(links);
            log.setUser(null);
        }
        return Response.status(200).entity(logs).build();
    }
    /**
     * Method to get link for self.
     * @param user  object
     * @return URI converted to string
     */
    private String getUriForSelf (User user){
        return context.getBaseUriBuilder()
                .path(UsersResource.class)
                .path(Long.toString(user.getId()))
                .build()
                .toString();
    }
    /**
     * Method to get link for Contact resource
     * @param user object
     * @return URI converted to string
     */
    private String getUriForUsersLogs(User user){
        return context.getBaseUriBuilder()
                .path(UsersResource.class)
                .path("{userId}/logs")
                .resolveTemplate("userId", user.getId())
                .build()
                .toString();
    }
    private String getUriForUserLog(Log log){
        return context.getBaseUriBuilder()
                .path(UsersResource.class)
                .path("{userId}/logs/{logId}")
                .resolveTemplate("userId", log.getUser().getId())
                .resolveTemplate("logId", log.getId())
                .build()
                .toString();
    }
}
