package com.medhat.weatherapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
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

        handleClicks();

    }

    public void initViews(){
        getByName      = findViewById(R.id.Landing_Page_Get_By_Name_CardView);
        getByLocation  = findViewById(R.id.Landing_Page_Get_By_Location_CardView);
    }

    public void handleClicks(){
        getByLocation.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, GetWeatherByLocationActivity.class);
            startActivity(intent);
        });

        getByName.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GetWeatherByNameActivity.class);
            startActivity(intent);
        });
    }
}