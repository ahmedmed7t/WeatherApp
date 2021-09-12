package com.medhat.weatherapp.ui.GetByName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.module.DaggerGetViewModelComponent;
import com.medhat.weatherapp.module.GetByNameModule;
import com.medhat.weatherapp.module.GetViewModelComponent;

public class GetWeatherByNameActivity extends AppCompatActivity {

    GetViewModelComponent module = DaggerGetViewModelComponent.create();
    WeatherByNameViewModel nameViewModel = module.getWeatherByName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_weather_by_name);

    }
}