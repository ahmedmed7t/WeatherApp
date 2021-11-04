package com.medhat.weatherapp.data.Repositories;


import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherByLocationResponse;
import com.medhat.weatherapp.data.api.GetByLocation.GetByLocationHelper;

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
