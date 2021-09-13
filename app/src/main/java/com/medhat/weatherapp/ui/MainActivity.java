package com.medhat.weatherapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.ui.GetByLocation.GetWeatherByLocationActivity;
import com.medhat.weatherapp.ui.GetByName.GetWeatherByNameActivity;

public class MainActivity extends AppCompatActivity {

    private CardView getByName, getByLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        getByLocation.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, GetWeatherByLocationActivity.class);
            startActivity(intent);
        });

        getByName.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GetWeatherByNameActivity.class);
            startActivity(intent);
        });

       // requestPermission();
    }

    public void requestPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    GetWeatherByLocationActivity.MY_PERMISSIONS_REQUEST_LOCATION);
            return;
        }
    }

    public void initViews(){
        getByName      = findViewById(R.id.Landing_Page_Get_By_Name_CardView);
        getByLocation  = findViewById(R.id.Landing_Page_Get_By_Location_CardView);
    }
}