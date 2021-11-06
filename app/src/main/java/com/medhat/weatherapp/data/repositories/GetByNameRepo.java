package com.medhat.weatherapp.data.repositories;

import android.annotation.SuppressLint;

import com.medhat.weatherapp.data.models.nameWeatherModels.WeatherByNameResponse;
import com.medhat.weatherapp.data.api.getByName.GetByNameHelper;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByNameRepo {

    private GetByNameHelper getByNameHelper;
    private ArrayList<WeatherByNameResponse> weatherResponse = new ArrayList<>();

    @Inject
    public GetByNameRepo(GetByNameHelper getByNameHelper) {
        this.getByNameHelper = getByNameHelper;
    }

    @SuppressLint("CheckResult")
    public Single<Response<WeatherByNameResponse>> getWeatherByName(String name){
        return getByNameHelper.getWeather(name);
    }

    public ArrayList<WeatherByNameResponse> getWeatherResponse() {
        return weatherResponse;
    }
}
