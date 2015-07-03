package com.steve.theshortreport.common;

import com.steve.theshortreport.service.response.*;

import java.util.List;
import java.util.Random;

/**
 * Created by steve-donnelly on 3/25/15.
 */
public class Util {
    public static int canIWearShorts(LatLongResponse latLongResponse){

        double compareTemp = latLongResponse.getMain().getTempMax();

        if (compareTemp < Constants.DEFAULT_MAYBE_THRESHOLD){
            return Constants.CANNOT_WEAR_SHORTS;
        }
        else if(compareTemp < Constants.DEFAULT_YES_THRESHOLD){
            boolean rainingToday = isRainy(latLongResponse);
            if (Constants.DEFAULT_IS_RAIN_A_FACTOR && rainingToday){
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
            if (Constants.DEFAULT_IS_RAIN_A_FACTOR && rainingToday){
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

    public static String getPhrase(int wearShortsOrNot){
        String[] phraseSet;
        switch (wearShortsOrNot){
            case Constants.CAN_WEAR_SHORTS:
                phraseSet = Constants.shortsPhrases;
                break;
            case Constants.MAYBE_WEAR_SHORTS:
                phraseSet = Constants.maybeShortsPhrases;
                break;
            case Constants.CANNOT_WEAR_SHORTS:
                phraseSet = Constants.noShortsPhrases;
                break;
            default:
                return "";
        }
        Random randomGenerator = new Random();
        return phraseSet[randomGenerator.nextInt(phraseSet.length)];
    }

    public static boolean isRainy(LatLongResponse latLongResponse){
        List<Weather> weatherList = latLongResponse.getWeather();
        for (Weather w : weatherList){
            if (w.getMain().equals("Rain")){
                return true;
            }
        }
        return false;
    }
}
