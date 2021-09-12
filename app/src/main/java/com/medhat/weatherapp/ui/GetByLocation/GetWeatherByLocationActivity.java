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
import android.widget.Toast;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherByLocationResponse;
import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherInfo;
import com.medhat.weatherapp.module.DaggerGetViewModelComponent;
import com.medhat.weatherapp.module.GetViewModelComponent;

import java.util.ArrayList;

public class GetWeatherByLocationActivity extends AppCompatActivity implements LocationListener{

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    GetViewModelComponent component = DaggerGetViewModelComponent.create();
    WeatherByLocationViewModel viewModel = component.getWeatherByLocation();

    private LocationManager locationManager;
    private double longitude, latitude;

    private RecyclerView weatherRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_weather_by_location);

        weatherRecyclerView = findViewById(R.id.Weather_By_Location_RecyclerView);
        weatherRecyclerView.setHasFixedSize(true);
        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);


        viewModel.getWeatherResponse().observe(this, weatherByLocationResponse -> {
            Log.v("nnnnnnnnnnnnnn","done with lenght" +weatherByLocationResponse.getList().size());
            viewModel.get5DaysWeather(weatherByLocationResponse);
            Log.v("nnnnnnnnnnnnnn","done with lenght" +weatherByLocationResponse.getList().size());
        });

        viewModel.getWeatherData().observe(this, weatherInfo -> {
            Log.v("nnnnnnnnnnnnnn","done with lenght" +weatherInfo.size());
            weatherRecyclerView.setAdapter(new LocationWeatherRecyclerAdapter(weatherInfo));
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(longitude != location.getLongitude() || latitude != location.getLatitude()){
            longitude = location.getLongitude();
            latitude  = location.getLatitude();

            viewModel.getWeatherByLocation(longitude,latitude);
        }
    }
}