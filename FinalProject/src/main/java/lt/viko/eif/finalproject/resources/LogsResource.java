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
        
         return Response.status(201).entity(logDao.getAll()).build();
    }
    @GET
    @Path("/{id}")
    public Response getLogById(@PathParam("id") int id) throws Exception {
        return Response.status(201).entity(logDao.getById(id)).build();
    }
    @GET
    @Path("/q")
    public Response get(@QueryParam("city") String city, 
            @QueryParam("address") String address, 
            @QueryParam("placeName") String placeName, 
            @QueryParam("placeType") String placeType) throws Exception {
        System.out.println("Fetching user info by "+ city +" "+ address+" "+ placeName+" "+ placeType);
        return Response.status(201).entity(logDao.getFilteredLogs(city, address, placeName, placeType)).build();
    }
}
