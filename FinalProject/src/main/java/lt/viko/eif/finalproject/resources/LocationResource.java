/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.resources;

import com.google.maps.errors.OverQueryLimitException;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResult;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lt.viko.eif.finalproject.api.GoogleAPIClient;
import lt.viko.eif.finalproject.bmi.BMI;
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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("")
public class LocationResource {

    @Context
    private UriInfo context;
    
    private UserDao userDao = new FinalProjectDatabase().getUserDao();
    private LogDao logDao = new FinalProjectDatabase().getLogDao();
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
    
    public Response getJson() throws Exception {
        //TODO return proper representation object
        /*
        List<String> stringai = new ArrayList<>();
        stringai.add("a");
        stringai.add("b");
        //new GenericEntity < List<String>> (stringai){}
        BMI bmi = new BMI (5000, 1.8);
        GoogleAPIClient gog = new  GoogleAPIClient();
        
        // return Response.status(200).entity(bmi.getCategoryName()).build();
         return Response.status(200).entity(gog.findNearbyPlace()).build();*/
        
        /*
        User user = new User(Integer.SIZE, 54.717177, 25.297325, 75, 1.8);
        
          List <PlaceType> activities = new ArrayList<>();
            activities.add(PlaceType.BAR);
            activities.add(PlaceType.CAFE);
            
            Log log = new Log();
            BMI bmi = new BMI(user.getMass(), user.getHeight());
            log.setUserCategory(bmi.getCategoryName());
            log.setBMIIndex(bmi.getBmiIndex());
            GoogleAPIClient gog = new  GoogleAPIClient();
            PlacesSearchResult result;
            for (PlaceType activity : activities) {
               result =  gog.findNearbyPlace (user.getLat(), user.getLng(), activity)[0];
               log.setPlaceName(result.name);
               log.setPlaceType(activity.toString());
               log.setAddress(result.vicinity.substring(result.vicinity.lastIndexOf(", ")).trim());
               log.setCity(result.vicinity.substring(result.vicinity.lastIndexOf(", ")));
            }
            user.getLogList().add(log);

            return Response.status(201).entity(user).build();*/
        return Response.status(201).build();
    }

    /**
     * PUT method for updating or creating an instance of LocationResource
     * @param content representation for the resource
     */
    @POST
    public Response putJson(User user) throws Exception {
        System.out.println(user.toString());

            user.setLogList(new ArrayList<Log>());
            BMI bmi = new BMI(user.getMass(), user.getHeight());
            user.setCategory(bmi.getCategoryName());
            user.setBmi(new BigDecimal(bmi.getBmiIndex()).setScale(2, RoundingMode.HALF_UP));
            
            GoogleAPIClient gog = new  GoogleAPIClient();
            PlacesSearchResult result;
            String vicinity = "";
            try{
            for (PlaceType activity : bmi.getActivities()) {
               result =  gog.findNearbyPlace(user.getLat(), user.getLng(), activity)[0];
               Log log = new Log();
               log.setPlaceName(result.name);
               log.setPlaceType(activity.toString());
               vicinity = result.vicinity;
               log.setAddress(vicinity.substring(0, vicinity.lastIndexOf(", ")-2));
               log.setCity(result.vicinity.substring(result.vicinity.lastIndexOf(", ")+2));
               user.getLogList().add(log);
               result = null;
            }
            
            user = userDao.addUser(user);
            System.out.print(""+user.getId());
             for (Log log: user.getLogList()){
                 log.setUser(user);
                 logDao.addLog(log);
                 log.setUser(null);
             }
             
             
            List<Link> links;
            links = new ArrayList<>();
            links.add(new Link(getUriForSelf(user), "self"));
            links.add(new Link(getUriForUsersLogs(user), "logs"));
            user.setLinks(links);
            return Response.status(201).entity(user).build();
            }
            catch (OverQueryLimitException e){
                return Response.status(500).entity("Error: " + e.getMessage()).build();
            }
            catch (Exception ex){
                return Response.status(404).entity("Error: " + ex.getMessage()).build();
            }
            
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
