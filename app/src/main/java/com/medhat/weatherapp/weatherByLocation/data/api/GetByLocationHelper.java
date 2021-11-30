package com.medhat.weatherapp.weatherByLocation.data.api;

import com.medhat.weatherapp.weatherByLocation.data.models.WeatherByLocationResponse;

import io.reactivex.Single;
import retrofit2.Response;

public interface GetByLocationHelper {
    Single<Response<WeatherByLocationResponse>> getWeather(double longitude, double latitude);
}
