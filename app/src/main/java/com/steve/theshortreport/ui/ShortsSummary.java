package com.steve.theshortreport.ui;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.steve.theshortreport.R;
import com.steve.theshortreport.common.Constants;
import com.steve.theshortreport.common.SettingsData;
import com.steve.theshortreport.common.Util;
import com.steve.theshortreport.service.LocationTracker;
import com.steve.theshortreport.service.response.LatLongResponse;
import com.steve.theshortreport.service.RestClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ShortsSummary extends ActionBarActivity {

    private RestClient restClient;
    private ProgressDialog progress;


    private TextView temperatureText;
    private TextView shortsText;
    private ImageView centralImage;
    private Button shortsButton;

    private Toolbar toolbar;
    private RelativeLayout borderLayout;
    private RelativeLayout innerLayout;

    private Animation fadeIn;
    private Animation fadeOut;

    private SettingsData settingsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shorts_summary);
    }

    @Override
    public void onResume(){
        super.onResume();
        SettingsData.loadSettingsData(this);

        restClient = new RestClient();

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        borderLayout = (RelativeLayout) findViewById(R.id.shorts_summary);
        innerLayout = (RelativeLayout) findViewById(R.id.main_background);
        borderLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        innerLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        progress = new ProgressDialog(this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage("Loading...");

        shortsButton = (Button) findViewById(R.id.shorts_button);
        shortsButton.setVisibility(View.VISIBLE);
        temperatureText = (TextView) findViewById(R.id.temperature_info);
        temperatureText.setVisibility(View.GONE);
        shortsText = (TextView) findViewById(R.id.shorts_text);
        shortsText.setVisibility(View.GONE);
        centralImage = (ImageView) findViewById(R.id.shorts_image);
        centralImage.setVisibility(View.GONE);

        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

        setUpButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings_menu){
            Intent optionsIntent = new Intent(this, SettingsActivity.class);
            startActivity(optionsIntent);
            return true;
        }
        return false;

    }

    private void setUpButton(){
        shortsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherForCurrentLocation();
            }
        });
    }

    private void wearShorts(LatLongResponse latLongResponse){
        setTemperatureText(latLongResponse);
        shortsText.setText(Util.getPhrase(Constants.CAN_WEAR_SHORTS));
        temperatureText.setVisibility(View.VISIBLE);
        temperatureText.startAnimation(fadeIn);
        shortsText.setVisibility(View.VISIBLE);
        shortsText.startAnimation(fadeIn);
        centralImage.setImageResource(R.drawable.shorts);
        centralImage.setVisibility(View.VISIBLE);
        centralImage.startAnimation(fadeIn);

        ObjectAnimator.ofObject(
                borderLayout,
                "backgroundColor",
                new ArgbEvaluator(),
                ((ColorDrawable)borderLayout.getBackground()).getColor(),
                getResources().getColor(R.color.colorPrimaryShorts))
                .setDuration(2000)
                .start();

        ObjectAnimator.ofObject(
                innerLayout,
                "backgroundColor",
                new ArgbEvaluator(),
                ((ColorDrawable)innerLayout.getBackground()).getColor(),
                getResources().getColor(R.color.colorLabelShorts))
                .setDuration(2000)
                .start();

        ObjectAnimator.ofObject(
                toolbar,
                "backgroundColor",
                new ArgbEvaluator(),
                ((ColorDrawable)toolbar.getBackground()).getColor(),
                getResources().getColor(R.color.colorSecondaryShorts))
                .setDuration(2000)
                .start();
    }

    private void maybeShorts(LatLongResponse latLongResponse){
        setTemperatureText(latLongResponse);
        shortsText.setText(Util.getPhrase(Constants.MAYBE_WEAR_SHORTS));
        temperatureText.setVisibility(View.VISIBLE);
        temperatureText.startAnimation(fadeIn);
        shortsText.setVisibility(View.VISIBLE);
        shortsText.startAnimation(fadeIn);
        centralImage.setImageResource(R.drawable.shorts_qm);
        centralImage.setVisibility(View.VISIBLE);
        centralImage.startAnimation(fadeIn);

        ObjectAnimator.ofObject(
                borderLayout,
                "backgroundColor",
                new ArgbEvaluator(),
                ((ColorDrawable)borderLayout.getBackground()).getColor(),
                getResources().getColor(R.color.colorPrimaryMaybe))
                .setDuration(2000)
                .start();

        ObjectAnimator.ofObject(
                innerLayout,
                "backgroundColor",
                new ArgbEvaluator(),
                ((ColorDrawable)innerLayout.getBackground()).getColor(),
                getResources().getColor(R.color.colorLabelMaybe))
                .setDuration(2000)
                .start();

        ObjectAnimator.ofObject(
                toolbar,
                "backgroundColor",
                new ArgbEvaluator(),
                ((ColorDrawable)toolbar.getBackground()).getColor(),
                getResources().getColor(R.color.colorSecondaryMaybe))
                .setDuration(2000)
                .start();
    }

    private void noShorts(LatLongResponse latLongResponse){
        setTemperatureText(latLongResponse);
        shortsText.setText(Util.getPhrase(Constants.CANNOT_WEAR_SHORTS));
        temperatureText.setVisibility(View.VISIBLE);
        temperatureText.startAnimation(fadeIn);
        shortsText.setVisibility(View.VISIBLE);
        shortsText.startAnimation(fadeIn);
        centralImage.setImageResource(R.drawable.shorts_x);
        centralImage.setVisibility(View.VISIBLE);
        centralImage.startAnimation(fadeIn);

        ObjectAnimator.ofObject(
                borderLayout,
                "backgroundColor",
                new ArgbEvaluator(),
                ((ColorDrawable)borderLayout.getBackground()).getColor(),
                getResources().getColor(R.color.colorPrimaryNo))
                .setDuration(2000)
                .start();

        ObjectAnimator.ofObject(
                innerLayout,
                "backgroundColor",
                new ArgbEvaluator(),
                ((ColorDrawable)innerLayout.getBackground()).getColor(),
                getResources().getColor(R.color.colorLabelNo))
                .setDuration(2000)
                .start();

        ObjectAnimator.ofObject(
                toolbar,
                "backgroundColor",
                new ArgbEvaluator(),
                ((ColorDrawable)toolbar.getBackground()).getColor(),
                getResources().getColor(R.color.colorSecondaryNo))
                .setDuration(2000)
                .start();
    }

    private void setTemperatureText(LatLongResponse latLongResponse){
        StringBuilder builder = new StringBuilder();
        builder.append("Current: ");
        builder.append(latLongResponse.getMain().getTemp());
        builder.append("\nDaily Max: ");
        builder.append(latLongResponse.getMain().getTempMax());
        if (Util.isRainy(latLongResponse)){
            builder.append("\nIt will rain today");
        }
        temperatureText.setText(builder.toString());
    }

    private void getWeatherForCurrentLocation(){
        LocationTracker locationTracker = new LocationTracker(ShortsSummary.this);
        if (locationTracker.canGetLocation()){
            double latitude = locationTracker.getLatitude();
            double longitude = locationTracker.getLongitude();

            progress.show();
            Callback<LatLongResponse> callback = new Callback<LatLongResponse>() {
                @Override
                public void success(LatLongResponse latLongResponse, Response response) {

                    progress.dismiss();

                    if (latLongResponse != null && latLongResponse.getMain() != null){
                        FadeOutAnimationListener buttonAnimationListener = new FadeOutAnimationListener(shortsButton);
                        buttonAnimationListener.onAnimationEnd(fadeOut);
                        shortsButton.startAnimation(fadeOut);

                        switch (Util.canIWearShorts(latLongResponse)){
                            case Constants.CAN_WEAR_SHORTS:
                                wearShorts(latLongResponse);
                                break;
                            case Constants.MAYBE_WEAR_SHORTS:
                                maybeShorts(latLongResponse);
                                break;
                            case Constants.CANNOT_WEAR_SHORTS:
                                noShorts(latLongResponse);
                                break;
                            default:
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
                    progress.dismiss();

                    Log.e(Constants.weatherAppErrorTag, "Error on making call to weather API");
                    Log.e(Constants.weatherAppErrorTag, error.getMessage());
                    Toast.makeText(getApplicationContext(), "Error on calling weather API", Toast.LENGTH_LONG).show();
                }

            };
            restClient.getWeatherService().getWeatherByLatLong(latitude, longitude, callback);
        } else {
            Toast.makeText(getApplicationContext(), "Can't get location.", Toast.LENGTH_LONG).show();
        }
    }
}
