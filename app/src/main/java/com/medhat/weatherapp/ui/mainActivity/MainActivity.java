package com.medhat.weatherapp.ui.mainActivity;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.databinding.ActivityMainBinding;
import com.medhat.weatherapp.ui.base.BaseActivity;
import com.medhat.weatherapp.ui.getByLocation.GetWeatherByLocationActivity;
import com.medhat.weatherapp.ui.getByName.GetWeatherByNameActivity;

import io.realm.Realm;

public class MainActivity extends BaseActivity implements MainHandler {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        // For View Binding
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        binding.LandingPageGetByNameCardView.setOnClickListener(v ->{
//
//        });

        // For Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(this);
    }

    @Override
    public void gotoLocationPage() {
        Intent intent = new Intent(MainActivity.this, GetWeatherByLocationActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoNamePage() {
        Intent intent = new Intent(MainActivity.this, GetWeatherByNameActivity.class);
        startActivity(intent);
    }

    @Override
    public void changeLanguageClicked() {
        changeLanguage();
    }
}