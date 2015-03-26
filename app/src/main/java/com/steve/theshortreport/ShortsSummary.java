package com.steve.theshortreport;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.steve.theshortreport.common.Constants;
import com.steve.theshortreport.common.UtilMethods;
import com.steve.theshortreport.service.LocationTracker;
import com.steve.theshortreport.service.response.LatLongResponse;
import com.steve.theshortreport.service.RestClient;

import java.text.DecimalFormat;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ShortsSummary extends ActionBarActivity {

    Button buttonGetWeatherInfo;
    LocationTracker locationTracker;
    RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shorts_summary);

        restClient = new RestClient();

        buttonGetWeatherInfo = (Button)findViewById(R.id.weather_info);
        buttonGetWeatherInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getWeatherForCurrentLocation();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shorts_summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getWeatherForCurrentLocation(){
        locationTracker = new LocationTracker(ShortsSummary.this);
        if (locationTracker.canGetLocation()){
            double latitude = locationTracker.getLatitude();
            double longitude = locationTracker.getLongitude();
            Callback callback = new Callback<LatLongResponse>() {
                @Override
                public void success(LatLongResponse latLongResponse, Response response) {
                    if (latLongResponse != null && latLongResponse.getMain() != null){

                        switch (UtilMethods.canIWearShorts(latLongResponse)){
                            case Constants.CAN_WEAR_SHORTS:
                                Toast.makeText(getApplicationContext(), "Wear shorts today!\nMax:" + latLongResponse.getMain().getTempMax() + "\nCurrent:" + latLongResponse.getMain().getTemp(), Toast.LENGTH_LONG).show();
                                break;
                            case Constants.MAYBE_WEAR_SHORTS:
                                Toast.makeText(getApplicationContext(), "Maybe wear shorts today.\nMax:" + latLongResponse.getMain().getTempMax() + "\nCurrent:" + latLongResponse.getMain().getTemp(), Toast.LENGTH_LONG).show();
                                break;
                            case Constants.CANNOT_WEAR_SHORTS:
                                Toast.makeText(getApplicationContext(), "No shorts today, bummer.\nMax:" + latLongResponse.getMain().getTempMax() + "\nCurrent:" + latLongResponse.getMain().getTemp(), Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), "Shouldn't ever see this.", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Bad response on weather request.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e(Constants.weatherAppErrorTag, "Error on making call to weather API");
                    Log.e(Constants.weatherAppErrorTag, error.getMessage());
                    Toast.makeText(getApplicationContext(), "Error on calling weather API", Toast.LENGTH_LONG).show();
                }

            };
            restClient.getWeatherService().getWeatherByLatLong((int)latitude, (int) longitude, callback);
        } else {
            Toast.makeText(getApplicationContext(), "Can't get location.", Toast.LENGTH_LONG).show();
        }
    }
}
