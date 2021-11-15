package com.medhat.weatherapp.data.repositories;

import android.annotation.SuppressLint;

import com.medhat.weatherapp.data.localDB.getByNameLocal.GetByNameLocalDB;
import com.medhat.weatherapp.data.localDB.localModels.Weather;
import com.medhat.weatherapp.data.models.nameWeatherModels.WeatherByNameResponse;
import com.medhat.weatherapp.data.api.getByName.GetByNameHelper;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByNameRepo {

    private GetByNameHelper getByNameHelper;
    private GetByNameLocalDB getByNameLocalDB;
    private ArrayList<WeatherByNameResponse> weatherResponse = new ArrayList<>();

    @Inject
    public GetByNameRepo(GetByNameHelper getByNameHelper, GetByNameLocalDB getByNameLocalDB) {
        this.getByNameHelper = getByNameHelper;
        this.getByNameLocalDB = getByNameLocalDB;
    }

    @SuppressLint("CheckResult")
    public Single<Response<WeatherByNameResponse>> getWeatherByName(String name){
        return getByNameHelper.getWeather(name);
    }

    public void saveWeatherRecord(Weather weather){
        getByNameLocalDB.addItem(weather);
    }

    public ArrayList<WeatherByNameResponse> getWeatherResponse() {
        return weatherResponse;
    }
}
