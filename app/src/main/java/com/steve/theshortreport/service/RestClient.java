package com.steve.theshortreport.service;

import com.steve.theshortreport.common.Constants;

import retrofit.RestAdapter;

/**
 * Created by steve-donnelly on 3/23/15.
 */
public class RestClient {

    private WeatherService weatherService;

    public RestClient(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Constants.weatherURL)
                .build();

        weatherService = restAdapter.create(WeatherService.class);
    }

    public WeatherService getWeatherService(){
        return weatherService;
    }
}
