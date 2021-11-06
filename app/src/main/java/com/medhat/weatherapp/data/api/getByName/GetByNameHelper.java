package com.medhat.weatherapp.data.api.getByName;


import com.medhat.weatherapp.data.models.nameWeatherModels.WeatherByNameResponse;

import io.reactivex.Single;
import retrofit2.Response;

public interface GetByNameHelper {
    Single<Response<WeatherByNameResponse>> getWeather(String cityName);
}
