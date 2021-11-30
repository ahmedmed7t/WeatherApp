package com.medhat.weatherapp.weatherByName.presentation.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.weatherByLocation.data.models.WeatherInfo;
import com.medhat.weatherapp.weatherByName.data.models.IWeatherItemHandler;
import com.medhat.weatherapp.databinding.ActivityGetWeatherByNameBinding;
import com.medhat.weatherapp.base.view.BaseActivity;
import com.medhat.weatherapp.weatherByLocation.presentation.adapter.LocationWeatherRecyclerAdapter;
import com.medhat.weatherapp.weatherByName.presentation.viewModel.WeatherByNameViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GetWeatherByNameActivity extends BaseActivity<WeatherByNameViewModel> implements IWeatherItemHandler {

    ActivityGetWeatherByNameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_get_weather_by_name);
        binding.setActivity(this);

        viewModel = new ViewModelProvider(this).get(WeatherByNameViewModel.class);

        initViews();
        listenToViewModelValues();

    }

    public void initViews(){
        binding.WeatherByNameRecyclerView.setHasFixedSize(true);
        binding.WeatherByNameRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void listenToViewModelValues(){
        viewModel.getErrorMessage().observe(this, s -> {
            new Handler().post(() -> {
                if (s instanceof String) {
                    Toast.makeText(GetWeatherByNameActivity.this, s.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(GetWeatherByNameActivity.this, getString(Integer.valueOf(s.toString())), Toast.LENGTH_LONG).show();
                }
            });
        });

        viewModel.getLiveResponse().observe(this, weatherByNameResponses -> {
            binding.WeatherByNameLoadingProgressBar.setVisibility(View.GONE);
            binding.WeatherByNameRecyclerView.setAdapter(new LocationWeatherRecyclerAdapter(weatherByNameResponses, this::weatherItemClicked));
        });
    }

    public void searchClickHandler(String searchValue){
        binding.WeatherByNameLoadingProgressBar.setVisibility(View.VISIBLE);
        viewModel.validateSearchValue(searchValue);
        binding.WeatherByNameRecyclerView.setAdapter(new LocationWeatherRecyclerAdapter(new ArrayList<>(), this::weatherItemClicked));
    }

    @Override
    public void weatherItemClicked(WeatherInfo weatherInfo) {
        viewModel.saveWeatherItem(weatherInfo);
    }
}