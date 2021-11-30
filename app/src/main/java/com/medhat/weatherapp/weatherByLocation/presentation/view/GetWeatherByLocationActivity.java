package com.medhat.weatherapp.weatherByLocation.presentation.view;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.medhat.weatherapp.weatherByLocation.data.models.WeatherInfo;
import com.medhat.weatherapp.weatherByName.data.models.IWeatherItemHandler;
import com.medhat.weatherapp.databinding.ActivityGetWeatherByLocationBinding;
import com.medhat.weatherapp.base.view.BaseActivity;
import com.medhat.weatherapp.weatherByLocation.presentation.adapter.LocationWeatherRecyclerAdapter;
import com.medhat.weatherapp.weatherByLocation.presentation.viewModel.WeatherByLocationViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GetWeatherByLocationActivity extends BaseActivity<WeatherByLocationViewModel>
        implements LocationListener, IWeatherItemHandler {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private ActivityGetWeatherByLocationBinding binding;

    private LocationManager locationManager;
    private double longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGetWeatherByLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(WeatherByLocationViewModel.class);

        initViews();
        listenToViewModelValues();

        getCurrentLocation();
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
        binding.WeatherByLocationRecyclerView.setHasFixedSize(true);
        binding.WeatherByLocationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.WeatherByLocationProgressBar.setVisibility(View.VISIBLE);
    }

    public void listenToViewModelValues(){
        viewModel.getWeatherData().observe(this, weatherInfo -> {
            binding.WeatherByLocationRecyclerView.setAdapter(new LocationWeatherRecyclerAdapter(weatherInfo, this::weatherItemClicked));
            binding.WeatherByLocationProgressBar.setVisibility(View.GONE);
        });

        viewModel.getErrorMessage().observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            binding.WeatherByLocationProgressBar.setVisibility(View.GONE);
        });
    }

    public void getCurrentLocation(){
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
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(this);
    }

    @Override
    public void weatherItemClicked(WeatherInfo weatherInfo) {
        viewModel.saveWeatherRecord(weatherInfo);
    }
}