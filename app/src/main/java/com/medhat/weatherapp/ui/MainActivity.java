package com.medhat.weatherapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.databinding.ActivityMainBinding;
import com.medhat.weatherapp.ui.getByLocation.GetWeatherByLocationActivity;
import com.medhat.weatherapp.ui.getByName.GetWeatherByNameActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // For View Binding
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        binding.LandingPageGetByNameCardView.setOnClickListener(v ->{
//
//        });

        // For Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
    }

    public void gotoLocationPage(){
        Intent intent = new Intent(MainActivity.this, GetWeatherByLocationActivity.class);
        startActivity(intent);
    }

    public void gotoNamePage(){
        Intent intent = new Intent(MainActivity.this, GetWeatherByNameActivity.class);
        startActivity(intent);
    }

}