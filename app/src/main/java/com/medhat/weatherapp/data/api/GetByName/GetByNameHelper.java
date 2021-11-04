package com.medhat.weatherapp.data.api.GetByName;


import com.medhat.weatherapp.data.Model.NameWeatherModels.WeatherByNameResponse;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

public interface GetByNameHelper {
    Single<Response<WeatherByNameResponse>> getWeather(String cityName);
}
