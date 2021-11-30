package com.medhat.weatherapp.weatherByName.data.api;


import com.medhat.weatherapp.weatherByName.data.models.WeatherByNameResponse;

import io.reactivex.Single;
import retrofit2.Response;

public interface GetByNameHelper {
    Single<Response<WeatherByNameResponse>> getWeather(String cityName);
}
