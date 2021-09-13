package com.medhat.weatherapp.ui.GetByLocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherByLocationResponse;
import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherInfo;
import com.medhat.weatherapp.module.DaggerGetViewModelComponent;
import com.medhat.weatherapp.module.GetViewModelComponent;
import com.medhat.weatherapp.ui.MainActivity;

import java.security.Permissions;
import java.util.ArrayList;

public class GetWeatherByLocationActivity extends AppCompatActivity implements LocationListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    GetViewModelComponent component = DaggerGetViewModelComponent.create();
    WeatherByLocationViewModel viewModel = component.getWeatherByLocation();

    private LocationManager locationManager;
    private double longitude, latitude;

    private RecyclerView weatherRecyclerView;
    private ProgressBar loadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_weather_by_location);
        initViews();

        viewModel.getWeatherResponse().observe(this, weatherByLocationResponse -> {
            viewModel.get5DaysWeather(weatherByLocationResponse);
        });

        viewModel.getWeatherData().observe(this, weatherInfo -> {
            weatherRecyclerView.setAdapter(new LocationWeatherRecyclerAdapter(weatherInfo));
            loadingProgress.setVisibility(View.GONE);
        });

        viewModel.getErrorMessage().observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            loadingProgress.setVisibility(View.GONE);
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, GetWeatherByLocationActivity.this);
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(longitude != location.getLongitude() || latitude != location.getLatitude()){
            longitude = location.getLongitude();
            latitude  = location.getLatitude();

            viewModel.getWeatherByLocation(longitude,latitude);
        }
    }

    public void initViews(){
        loadingProgress     = findViewById(R.id.Weather_By_Location_ProgressBar);
        weatherRecyclerView = findViewById(R.id.Weather_By_Location_RecyclerView);
        weatherRecyclerView.setHasFixedSize(true);
        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadingProgress.setVisibility(View.VISIBLE);
    }
}