package com.medhat.weatherapp.data.api.GetByName;


import com.medhat.weatherapp.data.Model.NameWeatherModels.WeatherByNameResponse;

import retrofit2.Callback;

public interface GetByNameHelper {
    void getWeather(String cityName,  Callback<WeatherByNameResponse> callback);
}
