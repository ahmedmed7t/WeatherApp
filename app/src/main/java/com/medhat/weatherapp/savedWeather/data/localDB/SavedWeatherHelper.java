package com.medhat.weatherapp.savedWeather.data.localDB;

import androidx.lifecycle.MutableLiveData;

import com.medhat.weatherapp.base.models.LocalWeather;
import com.medhat.weatherapp.weatherByLocation.data.models.WeatherInfo;

import java.util.ArrayList;
import java.util.List;


public interface SavedWeatherHelper {
    MutableLiveData<List<LocalWeather>> getAllWeather();
}
