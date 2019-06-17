/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.resources;

import com.google.maps.errors.ApiException;
import com.google.maps.errors.OverQueryLimitException;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResult;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
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
 * REST Web Service Class to get location by bmi.
 *
 * @author donatas
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("")
public class LocationResource {

    @Context
    private UriInfo context;

    private final UserDao userDao = new FinalProjectDatabase().getUserDao();
    private final LogDao logDao = new FinalProjectDatabase().getLogDao();

    /**
     * Creates a new instance of LocationResource
     */
    public LocationResource() {
    }

    /**
     * Post method for saving data to a database.
     *
     * @param content representation for the resource
     */
    @POST
    public Response putJson(User user) throws Exception {
        System.out.println(user.toString());

        GoogleAPIClient gog = new GoogleAPIClient();
        PlacesSearchResult result = null;
        try {
            if(user.getHeight() == 0.0 || user.getMass() == 0.0)
                throw new Exception("No height or mass");
            if(user.getLat() == 0.0|| user.getLng() == 0.0)
                throw new Exception("No latitude or longtitude");
            if(user.getLat() > 90|| user.getLng() > 180 || user.getLat() < -90 || user.getLng() < -180)
                throw new Exception("Bad latitude or longtitude");
            //Calculating data
            user.setLogList(new ArrayList<Log>());
            BMI bmi = new BMI(user.getMass(), user.getHeight());
            user.setCategory(bmi.getCategoryName());
            user.setBmi(new BigDecimal(bmi.getBmiIndex()).setScale(2, RoundingMode.HALF_UP));

            for (PlaceType activity : bmi.getActivities()) {
                //Finds nearest activities for given attributes
                for (PlacesSearchResult tempResult : gog.findNearbyPlace(user.getLat(), user.getLng(), activity)) {
                    if (tempResult.vicinity.lastIndexOf(", ") > 0) {
                        result = tempResult;
                        break;
                    }
                }
                //Sets found data to user log list
                if (result != null) {
                    Log log = new Log();
                    log.setPlaceName(result.name);
                    log.setPlaceType(activity.toString());
                    log.setAddress(result.vicinity.substring(0, result.vicinity.lastIndexOf(", ") - 2));
                    log.setCity(result.vicinity.substring(result.vicinity.lastIndexOf(", ") + 2));
                    user.getLogList().add(log);
                }
                result = null;
            }
            //If there was found nearby activities user and activities are saved in database
            if (!user.getLogList().isEmpty()) {
                user = userDao.addUser(user);
                System.out.print("" + user.getId());
                for (Log log : user.getLogList()) {
                    log.setUser(user);
                    logDao.addLog(log);
                    log.setUser(null);
                    log.setLinks(null);
                }
            } else {
                throw new Exception("Can't find any places for you" + bmi.getActivities().toString());
            }

            // Set HATEOAS
            List<Link> links;
            links = new ArrayList<>();
            links.add(new Link(getUriForSelf(user), "self"));
            links.add(new Link(getUriForUsersLogs(user), "logs"));
            user.setLinks(links);

            return Response.status(201).entity(user).build();
        } catch (OverQueryLimitException e) {
            return Response.status(500).entity("Error: " + e.getMessage()).build();
        } catch (Exception ex) {
            return Response.status(404).entity("Error: "+ ex.getMessage()).build();
        }

    }

    /**
     * Method to get link for self.
     *
     * @param user object
     * @return URI converted to string
     */
    private String getUriForSelf(User user) {
        return context.getBaseUriBuilder()
                .path(UsersResource.class)
                .path(Long.toString(user.getId()))
                .build()
                .toString();
    }

    /**
     * Method to get link for specific user logs.
     *
     * @param user object
     * @return URI converted to string
     */
    private String getUriForUsersLogs(User user) {
        return context.getBaseUriBuilder()
                .path(UsersResource.class)
                .path("{userId}/logs")
                .resolveTemplate("userId", user.getId())
                .build()
                .toString();
    }

    /**
     * Method to get link for specific user log;
     *
     * @param user object
     * @return URI converted to string
     */
    private String getUriForUserLog(Log log) {
        return context.getBaseUriBuilder()
                .path(UsersResource.class)
                .path("{userId}/logs/{logId}")
                .resolveTemplate("userId", log.getUser().getId())
                .resolveTemplate("logId", log.getId())
                .build()
                .toString();
    }
}
