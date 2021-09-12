package com.medhat.weatherapp.data.api.GetByLocation;

import android.util.Log;

import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherByLocationResponse;
import com.medhat.weatherapp.data.RetrofitWebService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetByLocationHelperImp implements GetByLocationHelper{

    private Response<WeatherByLocationResponse> responseCall = null;
    @Inject
    public GetByLocationHelperImp() {
    }


    @Override
    public void getWeather(double longitude, double latitude,
                           Callback<WeatherByLocationResponse> callback ) {
        RetrofitWebService.getService().getWeatherByLocation(longitude,latitude).enqueue(callback);
    }
}
