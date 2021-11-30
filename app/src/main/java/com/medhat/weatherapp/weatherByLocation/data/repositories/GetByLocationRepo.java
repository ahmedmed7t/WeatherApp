package com.medhat.weatherapp.weatherByLocation.data.repositories;


import com.medhat.weatherapp.weatherByLocation.data.localDB.GetByLocationLocalDB;
import com.medhat.weatherapp.savedWeather.data.models.Weather;
import com.medhat.weatherapp.weatherByLocation.data.models.WeatherByLocationResponse;
import com.medhat.weatherapp.weatherByLocation.data.api.GetByLocationHelper;
import com.medhat.weatherapp.base.repository.BaseRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByLocationRepo extends BaseRepository {

    private final GetByLocationHelper locationHelper;
    private final GetByLocationLocalDB getByLocationLocalDB;

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
