package com.medhat.weatherapp.base.retrofit;

import com.medhat.weatherapp.weatherByLocation.data.models.WeatherByLocationResponse;
import com.medhat.weatherapp.weatherByName.data.models.WeatherByNameResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitService {

    @GET("/data/2.5/forecast")
    Single<Response<WeatherByLocationResponse>> getWeatherByLocation(
            @Query("lon") double longitude,
            @Query("lat") double latitude);

    @GET("/data/2.5/weather")
    Single<Response<WeatherByNameResponse>> getWeatherByName(
            @Query("q") String cityName);

}
