package com.medhat.weatherapp.data.api.GetByLocation;

import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherByLocationResponse;

import io.reactivex.Single;
import retrofit2.Response;

public interface GetByLocationHelper {
    Single<Response<WeatherByLocationResponse>> getWeather(double longitude, double latitude);
}
