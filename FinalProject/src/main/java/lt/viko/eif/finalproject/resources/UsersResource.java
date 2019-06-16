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
import lt.viko.eif.finalproject.dataaccess.UserDao;
import lt.viko.eif.finalproject.models.Log;
import lt.viko.eif.finalproject.models.User;

/**
 * REST Web Service
 *
 * @author donatas
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Context
    private UriInfo context;
    
    private UserDao userDao = new FinalProjectDatabase().getUserDao();
    private LogDao logDao = new FinalProjectDatabase().getLogDao();
    /**
     * Creates a new instance of LocationResource
     */
    public UsersResource() {
    }

    /**
     * Retrieves representation of an instance of lt.viko.eif.finalproject.resources.LocationResource
     * @return an instance of java.lang.String
     */
    @GET
    public Response getJson() throws Exception {
        System.out.println("Fetching all user info...");
        
        List<User> users = null;
        try{
        users = userDao.getAll();}
        catch (NotFoundException ex){
            return Response.status(204).entity(users).build();
        }
        List<Link> links;
        for (User user: users){
            links = new ArrayList<>();
            links.add(new Link(getUriForSelf(user), "self"));
            links.add(new Link(getUriForUsersLogs(user), "logs"));
            user.setLinks(links);
        }
        return Response.status(200).entity(users).build();
    }
    
    @GET
    @Path("/q")
    public Response get(@QueryParam("category") String category, 
            @DefaultValue("91")@QueryParam("lat") double lat, 
            @DefaultValue("181")@QueryParam("lng") double lng, 
            @DefaultValue("0")@QueryParam("mass") double mass,
            @DefaultValue("0")@QueryParam("height") double height,
            @DefaultValue("0")@QueryParam("bmi") BigDecimal bmi) throws Exception {
        System.out.println("Fetching user info by "+ category  +" "+ lat+" "+ lng+" "+ mass+" "+ height+" "+ bmi);
        List<User> users = null;
        try{
            users = userDao.getFilteredUsers( category, lat, lng, mass,  height, bmi);}
        catch (NotFoundException ex){
            return Response.status(204).entity(users).build();
        }
        catch (SQLException ex) {
             return Response.status(404).entity("Error: " +ex.getMessage()).build();
        }
        List<Link> links;
        for (User user: users){
            links = new ArrayList<>();
            links.add(new Link(getUriForSelf(user), "self"));
            links.add(new Link(getUriForUsersLogs(user), "logs"));
            user.setLinks(links);
        }
        return Response.status(200).entity(users).build();
    }
    @GET
    @Path("{userid}/logs")
    public Response getLogsById(@PathParam("userid") int userid) throws Exception {
        List<Log> logs =null;
        try{
        logs = logDao.getUserLogs(userid);}
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
    @GET
    @Path("{userid}/logs/{id}")
    public Response getLogById(@PathParam("userid") int userid,@PathParam("id") int id) throws Exception {
        Log log =null;
        try{
        log = logDao.getUserLogById(userid, id);}
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
    @Path("/{id}")
    public Response getUserById(@PathParam("id") int id) throws Exception {
        User user = null;
        try{
            user =  userDao.getById(id);}
        catch (NotFoundException ex){
            return Response.status(204).entity(user).build();
        }
        catch (SQLException ex) {
             return Response.status(404).entity("Error: " +ex.getMessage()).build();
        }
        List<Link> links;
            links = new ArrayList<>();
            links.add(new Link(getUriForSelf(user), "self"));
            links.add(new Link(getUriForUsersLogs(user), "logs"));
            user.setLinks(links);
        return Response.status(200).entity(user).build();
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
