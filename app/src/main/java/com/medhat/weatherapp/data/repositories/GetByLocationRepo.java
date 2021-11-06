package com.medhat.weatherapp.data.repositories;


import com.medhat.weatherapp.data.models.locationWeatherModels.WeatherByLocationResponse;
import com.medhat.weatherapp.data.api.getByLocation.GetByLocationHelper;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByLocationRepo {

    private GetByLocationHelper locationHelper;

    @Inject
    public GetByLocationRepo(GetByLocationHelper locationHelper) {
        this.locationHelper = locationHelper;
    }

    public Single<Response<WeatherByLocationResponse>> getWeatherByLocation(double longitude, double latitude){
        return locationHelper.getWeather(longitude,latitude);
    }
}
