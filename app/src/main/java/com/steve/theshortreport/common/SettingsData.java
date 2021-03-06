package com.steve.theshortreport.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by steve-donnelly on 7/3/15.
 */
public class SettingsData {

    private static SettingsData settingsData;

    private Integer shortsThreshold;
    private Integer maybeThreshold;
    private Boolean rainInfluence;

    public static void loadSettingsData(Context context){
        settingsData = new SettingsData();
        settingsData.loadPreferences(context);
    }

    public static SettingsData getSettingsData() {
        return settingsData;
    }

    public Integer getShortsThreshold() {
        return shortsThreshold;
    }

    public void setShortsThreshold(Integer shortsThreshold) {
        this.shortsThreshold = shortsThreshold;
    }

    public Integer getMaybeThreshold() {
        return maybeThreshold;
    }

    public void setMaybeThreshold(Integer maybeThreshold) {
        this.maybeThreshold = maybeThreshold;
    }

    public Boolean getRainInfluence() {
        return rainInfluence;
    }

    public void setRainInfluence(Boolean rainInfluence) {
        this.rainInfluence = rainInfluence;
    }

    private void loadPreferences(Context context) {
        SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(context);

        try{
            setShortsThreshold(Integer.valueOf(app_preferences.getString("shortsThreshold", Constants.DEFAULT_YES_THRESHOLD)));
        }
        catch(NumberFormatException nfe){
            app_preferences.edit().putString("shortsThreshold", Constants.DEFAULT_YES_THRESHOLD);
            app_preferences.edit().apply();
            setShortsThreshold(Integer.valueOf(Constants.DEFAULT_YES_THRESHOLD));
        }

        try{
            setMaybeThreshold(Integer.valueOf(app_preferences.getString("maybeThreshold", Constants.DEFAULT_MAYBE_THRESHOLD)));
        }
        catch(NumberFormatException nfe){
            app_preferences.edit().putString("maybeThreshold", Constants.DEFAULT_MAYBE_THRESHOLD);
            app_preferences.edit().apply();
            setMaybeThreshold(Integer.valueOf(Constants.DEFAULT_MAYBE_THRESHOLD));
        }

        setRainInfluence(app_preferences.getBoolean("rainInfluence", Constants.DEFAULT_IS_RAIN_A_FACTOR));
    }
}
