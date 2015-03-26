package com.steve.theshortreport.service.response;

import com.google.gson.annotations.Expose;

public class Coord {

    @Expose
    private Integer lon;
    @Expose
    private Integer lat;

    /**
     *
     * @return
     * The lon
     */
    public Integer getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(Integer lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     * The lat
     */
    public Integer getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(Integer lat) {
        this.lat = lat;
    }

}