package com.steve.theshortreport.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.steve.theshortreport.R;
import com.steve.theshortreport.common.Constants;
import com.steve.theshortreport.common.UtilMethods;
import com.steve.theshortreport.service.LocationTracker;
import com.steve.theshortreport.service.response.LatLongResponse;
import com.steve.theshortreport.service.RestClient;



import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ShortsSummary extends BaseActivity {

    Button buttonGetWeatherInfo;
    LocationTracker locationTracker;
    RestClient restClient;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restClient = new RestClient();
        progress = new ProgressDialog(this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //progress.setTitle("Loading");
        progress.setMessage("Loading...");

        buttonGetWeatherInfo = (Button)findViewById(R.id.weather_info);
        buttonGetWeatherInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getWeatherForCurrentLocation();
            }
        });
    }

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_shorts_summary;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getWeatherForCurrentLocation(){
        locationTracker = new LocationTracker(ShortsSummary.this);
        if (locationTracker.canGetLocation()){
            double latitude = locationTracker.getLatitude();
            double longitude = locationTracker.getLongitude();

            progress.show();
            Callback callback = new Callback<LatLongResponse>() {
                @Override
                public void success(LatLongResponse latLongResponse, Response response) {

                    progress.dismiss();

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
                        progress.dismiss();

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
