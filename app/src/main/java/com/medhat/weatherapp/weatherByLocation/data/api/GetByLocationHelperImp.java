package com.medhat.weatherapp.weatherByLocation.data.api;


import com.medhat.weatherapp.base.retrofit.RetrofitService;
import com.medhat.weatherapp.weatherByLocation.data.models.WeatherByLocationResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByLocationHelperImp implements GetByLocationHelper {

    RetrofitService retrofitService;

    @Inject
    public GetByLocationHelperImp(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    @Override
    public Single<Response<WeatherByLocationResponse>> getWeather(double longitude, double latitude) {
       return retrofitService.getWeatherByLocation(longitude,latitude);
    }
}
