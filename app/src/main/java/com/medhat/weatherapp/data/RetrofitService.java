package com.medhat.weatherapp.data;

import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherByLocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitService {

    @GET("/data/2.5/forecast")
    Call<WeatherByLocationResponse> getWeatherByLocation(
            @Query("lon") double longitude,
            @Query("lat") double latitude);

}
