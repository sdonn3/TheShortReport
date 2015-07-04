package com.steve.theshortreport.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.steve.theshortreport.R;
import com.steve.theshortreport.common.SettingsData;

/**
 * Created by steve-donnelly on 7/3/15.
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
