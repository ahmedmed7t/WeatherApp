package com.medhat.weatherapp.savedWeather.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.medhat.weatherapp.base.models.LocalWeather;
import com.medhat.weatherapp.savedWeather.data.localDB.SavedWeatherHelper;
import com.medhat.weatherapp.weatherByLocation.data.models.WeatherInfo;
import com.medhat.weatherapp.base.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SavedWeatherRepo extends BaseRepository {

    private SavedWeatherHelper savedWeatherHelper;

    @Inject
    public SavedWeatherRepo(SavedWeatherHelper savedWeatherHelper) {
        this.savedWeatherHelper = savedWeatherHelper;
    }

    public MutableLiveData<List<LocalWeather>> getSavedWeather(){
        return savedWeatherHelper.getAllWeather();
    }
}
