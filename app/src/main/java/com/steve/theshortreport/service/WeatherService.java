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
    @GET("/weather?units=imperial&APPID=b8b2aed9d730757b91ccaf62fb127906")
    void getWeatherByLatLong(@Query("lat") double latitude, @Query("lon") double longitude, Callback<LatLongResponse> callback);
}