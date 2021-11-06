package com.medhat.weatherapp.data.api.getByLocation;

import com.medhat.weatherapp.data.models.locationWeatherModels.WeatherByLocationResponse;

import io.reactivex.Single;
import retrofit2.Response;

public interface GetByLocationHelper {
    Single<Response<WeatherByLocationResponse>> getWeather(double longitude, double latitude);
}
