package com.medhat.weatherapp.data.api.GetByLocation;

import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherByLocationResponse;

import retrofit2.Callback;
import retrofit2.Response;

public interface GetByLocationHelper {
    void getWeather(double longitude, double latitude, Callback<WeatherByLocationResponse> callback);
}
