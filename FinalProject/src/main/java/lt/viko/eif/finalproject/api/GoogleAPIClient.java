/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.api;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.InvalidRequestException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.RankBy;
import java.io.IOException;

/**
 * Class used to work with google API.
 *
 * @author donatas
 */
public class GoogleAPIClient {

    /**
     * Google API key
     */
    private static final String apiKey = "AIzaSyDtyaE69M3eFNC7RVd4xdoFS3D9a2bd124";

    /**
     * Method to get nearby place by its type.
     *
     * @param lat Latitude
     * @param lng Longitude
     * @param placetType
     * @return Array of PlacesSearchResult
     * @throws ApiException
     * @throws InterruptedException
     * @throws IOException
     */
    public PlacesSearchResult[] findNearbyPlace(double lat, double lng, PlaceType placetType) throws InvalidRequestException, ApiException, InterruptedException, IOException {
       
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
