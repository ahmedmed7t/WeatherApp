package com.medhat.weatherapp.data.repositories;


import com.medhat.weatherapp.data.localDB.getByLocationLocal.GetByLocationLocalDB;
import com.medhat.weatherapp.data.localDB.localModels.Weather;
import com.medhat.weatherapp.data.models.locationWeatherModels.WeatherByLocationResponse;
import com.medhat.weatherapp.data.api.getByLocation.GetByLocationHelper;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByLocationRepo {

    private GetByLocationHelper locationHelper;
    private GetByLocationLocalDB getByLocationLocalDB;

    @Inject
    public GetByLocationRepo(GetByLocationHelper locationHelper, GetByLocationLocalDB getByLocationLocalDB) {
        this.locationHelper = locationHelper;
        this.getByLocationLocalDB = getByLocationLocalDB;
    }

    public Single<Response<WeatherByLocationResponse>> getWeatherByLocation(double longitude, double latitude){
        return locationHelper.getWeather(longitude,latitude);
    }

    public void saveWeatherRecord(Weather weather){
        getByLocationLocalDB.addItem(weather);
    }
}
