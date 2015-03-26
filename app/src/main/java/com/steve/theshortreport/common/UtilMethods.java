package com.steve.theshortreport.common;

import com.steve.theshortreport.service.response.*;

import java.util.List;

/**
 * Created by steve-donnelly on 3/25/15.
 */
public class UtilMethods {
    public static int canIWearShorts(LatLongResponse latLongResponse){

        double compareTemp = (latLongResponse.getMain().getTemp() + latLongResponse.getMain().getTempMax()) / 2;

        if (compareTemp < Constants.DEFAULT_MAYBE_THRESHOLD){
            return Constants.CANNOT_WEAR_SHORTS;
        }
        else if(compareTemp < Constants.DEFAULT_YES_THRESHOLD){
            boolean rainingToday = isRainy(latLongResponse);
            if (Constants.DEFAULT_RAIN_FACTOR && rainingToday){
                return Constants.MAYBE_WEAR_SHORTS;
            }
            else if (!rainingToday) {
                return Constants.MAYBE_WEAR_SHORTS;
            }
            else{
                return Constants.CANNOT_WEAR_SHORTS;
            }
        }
        else{
            boolean rainingToday = isRainy(latLongResponse);
            if (Constants.DEFAULT_RAIN_FACTOR && rainingToday){
                return Constants.CAN_WEAR_SHORTS;
            }
            else if (!rainingToday) {
                return Constants.CAN_WEAR_SHORTS;
            }
            else{
                return Constants.CANNOT_WEAR_SHORTS;
            }
        }
    }

    public static double convertKelvinToFahrenheit(double kelvin){
        return kelvin * (9.0/5.0) - 459.67;
    }

    public static double convertFahrenheitToKelvin(double fahrenheit){
        return (fahrenheit + 459.67) * (5.0/9.0);
    }

    private static boolean isRainy(LatLongResponse latLongResponse){
        List<Weather> weatherList = latLongResponse.getWeather();
        for (Weather w : weatherList){
            if (w.getMain().equals("Rain")){
                return true;
            }
        }
        return false;
    }

    public static double convertKelvinToCelsius(double kelvin){
        return kelvin - 273.15;
    }

    public static double convertCelsiusToKelvin(double celsius){
        return celsius + 273.15;
    }


}
