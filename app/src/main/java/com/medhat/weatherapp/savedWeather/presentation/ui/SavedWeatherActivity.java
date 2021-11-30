package com.medhat.weatherapp.savedWeather.presentation.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.medhat.weatherapp.savedWeather.presentation.viewModel.SavedWeatherViewModel;
import com.medhat.weatherapp.weatherByLocation.data.models.WeatherInfo;
import com.medhat.weatherapp.weatherByName.data.models.IWeatherItemHandler;
import com.medhat.weatherapp.databinding.ActivitySavedWeatherBinding;
import com.medhat.weatherapp.base.view.BaseActivity;
import com.medhat.weatherapp.weatherByLocation.presentation.adapter.LocationWeatherRecyclerAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SavedWeatherActivity extends BaseActivity<SavedWeatherViewModel> implements IWeatherItemHandler {


    private ActivitySavedWeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySavedWeatherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SavedWeatherViewModel.class);

        initViews();

        listenToViewModelValues();
    }

    public void initViews(){
        binding.SavedWeatherRecyclerView.setHasFixedSize(true);
        binding.SavedWeatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void listenToViewModelValues(){
        viewModel.getAllSavedWeather().observe(this, weatherInfo -> {
            binding.SavedWeatherRecyclerView.setAdapter(new LocationWeatherRecyclerAdapter(viewModel.convertLocalWeatherToInfo(weatherInfo), this));
        });
    }

    @Override
    public void weatherItemClicked(WeatherInfo weatherInfo) {

    }
}