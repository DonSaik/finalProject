/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.api;

import com.google.maps.FindPlaceFromTextRequest;
import com.google.maps.FindPlaceFromTextRequest.InputType;
import com.google.maps.FindPlaceFromTextRequest.LocationBiasIP;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.RankBy;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import lt.viko.eif.finalproject.models.User;

/**
 *
 * @author donatas
 */
public class GoogleAPIClient {
    
    private static final String apiKey = "AIzaSyD_wwGcgZy85oMJoqPwzd0gGVRh10yZS8Y";
    
    public String response (){
        Client client = ClientBuilder.newClient();
        
        WebTarget target = client.target("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=mongolian%20grill&inputtype=textquery&fields=photos,formatted_address,name,opening_hours,rating&locationbias=circle:2000@47.6918452,-122.2226413&key=" + apiKey);
        
        
        
        return target.request(MediaType.APPLICATION_JSON).get(String.class);
    }
    
    public FindPlaceFromText findPlaceTest () throws ApiException, InterruptedException, IOException{
        GeoApiContext context = new GeoApiContext.Builder()
    .apiKey(apiKey)
    .build();
      FindPlaceFromText result = PlacesApi.findPlaceFromText(context, "Museum of Contemporary Art Australia", InputType.TEXT_QUERY)
              .fields(
                  FindPlaceFromTextRequest.FieldMask.PHOTOS,
                  FindPlaceFromTextRequest.FieldMask.FORMATTED_ADDRESS,
                  FindPlaceFromTextRequest.FieldMask.NAME,
                  FindPlaceFromTextRequest.FieldMask.RATING,
                  FindPlaceFromTextRequest.FieldMask.OPENING_HOURS,
FindPlaceFromTextRequest.FieldMask.GEOMETRY)
              .locationBias(new LocationBiasIP())
                      .await();
      return result;
    }
    
    public  PlacesSearchResult[] findNearbyPlace (double lat, double lng, PlaceType placetType) throws ApiException, InterruptedException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
    .apiKey(apiKey)
                .disableRetries()
    .build();
        // location 
      //LatLng location = new LatLng(54.717177, 25.297325);
      LatLng location = new LatLng(lat, lng);
        PlacesSearchResponse result = PlacesApi.nearbySearchQuery(context, location)
          //.radius(5000)
          .type(placetType)
          .rankby(RankBy.DISTANCE)
          .await();
        
      return result.results;
    }
    
    
}
