package com.steve.theshortreport.service.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @Expose
    private Double speed;
    @Expose
    private Double deg;
    @SerializedName("var_beg")
    @Expose
    private Double varBeg;
    @SerializedName("var_end")
    @Expose
    private Double varEnd;

    /**
     *
     * @return
     * The speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     * The speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     * The deg
     */
    public Double getDeg() {
        return deg;
    }

    /**
     *
     * @param deg
     * The deg
     */
    public void setDeg(Double deg) {
        this.deg = deg;
    }

    /**
     *
     * @return
     * The varBeg
     */
    public Double getVarBeg() {
        return varBeg;
    }

    /**
     *
     * @param varBeg
     * The var_beg
     */
    public void setVarBeg(Double varBeg) {
        this.varBeg = varBeg;
    }

    /**
     *
     * @return
     * The varEnd
     */
    public Double getVarEnd() {
        return varEnd;
    }

    /**
     *
     * @param varEnd
     * The var_end
     */
    public void setVarEnd(Double varEnd) {
        this.varEnd = varEnd;
    }

}