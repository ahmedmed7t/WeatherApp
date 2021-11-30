package com.medhat.weatherapp.weatherByName.data.repositories;

import android.annotation.SuppressLint;

import com.medhat.weatherapp.weatherByName.data.local.GetByNameLocalDB;
import com.medhat.weatherapp.savedWeather.data.models.Weather;
import com.medhat.weatherapp.weatherByName.data.models.WeatherByNameResponse;
import com.medhat.weatherapp.weatherByName.data.api.GetByNameHelper;
import com.medhat.weatherapp.base.repository.BaseRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByNameRepo extends BaseRepository {

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
