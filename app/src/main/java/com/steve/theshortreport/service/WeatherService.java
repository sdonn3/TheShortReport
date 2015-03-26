package com.steve.theshortreport.service;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

import com.steve.theshortreport.service.response.*;

/**
 * Created by steve-donnelly on 3/22/15.
 */
public interface WeatherService {
    // Get the weather based on the current location of the user
    @GET("/weather")
    public void getWeatherByLatLong(@Query("lat") int latitude, @Query("lon") int longitude, Callback<LatLongResponse> callback);
}