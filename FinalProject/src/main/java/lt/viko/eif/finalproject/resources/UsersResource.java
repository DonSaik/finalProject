/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.resources;

import java.math.BigDecimal;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lt.viko.eif.finalproject.dataaccess.FinalProjectDatabase;
import lt.viko.eif.finalproject.dataaccess.LogDao;
import lt.viko.eif.finalproject.dataaccess.UserDao;

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
        return Response.status(201).entity(userDao.getAll()).build();
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
        return Response.status(201).entity(userDao.getFilteredUsers( category, lat, lng, mass,  height, bmi)).build();
    }
    @GET
    @Path("{userid}/logs")
    public Response getLogById(@PathParam("userid") int userid) throws Exception {
        return Response.status(201).entity(logDao.getUserLogs(userid)).build();
    }
    @GET
    @Path("{userid}/logs/{id}")
    public Response getLogById(@PathParam("userid") int userid,@PathParam("id") int id) throws Exception {
        return Response.status(201).entity(logDao.getUserLogById(userid, id)).build();
    }
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") int id) throws Exception {
        return Response.status(201).entity(userDao.getById(id)).build();
    }
}
