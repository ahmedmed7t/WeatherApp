package com.medhat.weatherapp.ui.GetByLocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.module.DaggerGetViewModelComponent;
import com.medhat.weatherapp.module.GetViewModelComponent;

public class GetWeatherByLocationActivity extends AppCompatActivity implements LocationListener{

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    GetViewModelComponent component = DaggerGetViewModelComponent.create();
    WeatherByLocationViewModel viewModel = component.getWeatherByLocation();

    private LocationManager locationManager;
    private double longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_weather_by_location);

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
    public void onLocationChanged(@NonNull Location location) {
        if(longitude != location.getLongitude() || latitude != location.getLatitude()){
            longitude = location.getLongitude();
            latitude  = location.getLatitude();

            Toast.makeText(GetWeatherByLocationActivity.this, "long  = "+ longitude +"lat = "+latitude, Toast.LENGTH_SHORT).show();
        }
    }
}