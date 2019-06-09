/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.resources;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lt.viko.eif.finalproject.bmi.BMI;

/**
 * REST Web Service
 *
 * @author donatas
 */
@Path("location")
public class LocationResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LocationResource
     */
    public LocationResource() {
    }

    /**
     * Retrieves representation of an instance of lt.viko.eif.finalproject.resources.LocationResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() throws Exception {
        //TODO return proper representation object
        List<String> stringai = new ArrayList<>();
        stringai.add("a");
        stringai.add("b");
        //new GenericEntity < List<String>> (stringai){}
        BMI bmi = new BMI (5000, 1.8);
         return Response.status(200).entity(bmi.getCategoryName()).build();
    }

    /**
     * PUT method for updating or creating an instance of LocationResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
