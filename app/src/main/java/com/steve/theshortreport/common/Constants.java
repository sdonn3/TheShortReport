package com.steve.theshortreport.common;

/**
 * Created by steve-donnelly on 3/22/15.
 */
public interface Constants {
    public String weatherURL = "http://api.openweathermap.org/data/2.5";
    public String weatherAppErrorTag = "weather.error";

    public final int CAN_WEAR_SHORTS = 0;
    public final int MAYBE_WEAR_SHORTS = 1;
    public final int CANNOT_WEAR_SHORTS = 2;

    public final double DEFAULT_YES_THRESHOLD = 294.261;
    public final double DEFAULT_MAYBE_THRESHOLD = 288.706;
    public final boolean DEFAULT_RAIN_FACTOR = false;
}
