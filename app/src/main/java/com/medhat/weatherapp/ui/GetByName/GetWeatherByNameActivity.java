package com.medhat.weatherapp.ui.GetByName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherInfo;
import com.medhat.weatherapp.data.Model.NameWeatherModels.WeatherByNameResponse;
import com.medhat.weatherapp.module.DaggerGetViewModelComponent;
import com.medhat.weatherapp.module.GetByNameModule;
import com.medhat.weatherapp.module.GetViewModelComponent;
import com.medhat.weatherapp.ui.GetByLocation.LocationWeatherRecyclerAdapter;

import java.util.ArrayList;

public class GetWeatherByNameActivity extends AppCompatActivity {

    GetViewModelComponent module = DaggerGetViewModelComponent.create();
    WeatherByNameViewModel nameViewModel = module.getWeatherByName();

    private EditText citiesNameValue;
    private ImageView searchButton;
    private RecyclerView weatherRecycler;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_weather_by_name);
        initViews();


        searchButton.setOnClickListener( v ->{
            loading.setVisibility(View.VISIBLE);
            nameViewModel.validateSearchValue(citiesNameValue.getText().toString(),this);
            weatherRecycler.setAdapter(new LocationWeatherRecyclerAdapter(new ArrayList<>()));
        });

        nameViewModel.getErrorMessage().observe(this, s -> {
            Toast.makeText(GetWeatherByNameActivity.this,s , Toast.LENGTH_LONG).show();
        });

        nameViewModel.getLiveResponse().observe(this, weatherByNameResponses -> {
            loading.setVisibility(View.GONE);
            weatherRecycler.setAdapter(new LocationWeatherRecyclerAdapter(weatherByNameResponses));
        });

        nameViewModel.getResponseCount().observe(this, integer -> nameViewModel.validateResponse());


    }

    public void initViews(){
        citiesNameValue     = findViewById(R.id.Weather_By_Name_Value_EditText);
        searchButton        = findViewById(R.id.Weather_By_Name_Search_Button);
        weatherRecycler     = findViewById(R.id.Weather_By_Name_RecyclerView);
        loading             = findViewById(R.id.Weather_By_Name_Loading_ProgressBar);
        weatherRecycler.setHasFixedSize(true);
        weatherRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}