package com.medhat.weatherapp.weatherByName.data.api;

import com.medhat.weatherapp.base.retrofit.RetrofitService;
import com.medhat.weatherapp.weatherByName.data.models.WeatherByNameResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByNameHelperImp implements GetByNameHelper {

    RetrofitService retrofitService;
    @Inject
    public GetByNameHelperImp(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    @Override
    public Single<Response<WeatherByNameResponse>> getWeather(String cityName) {
        return retrofitService.getWeatherByName(cityName);
    }
}
