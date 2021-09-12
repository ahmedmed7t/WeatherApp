package com.medhat.weatherapp.data.api.GetByName;

import com.medhat.weatherapp.data.Model.NameWeatherModels.WeatherByNameResponse;
import com.medhat.weatherapp.data.RetrofitWebService;

import javax.inject.Inject;

import retrofit2.Callback;

public class GetByNameHelperImp implements GetByNameHelper{

    @Inject
    public GetByNameHelperImp() {
    }

    @Override
    public void getWeather(String cityName, Callback<WeatherByNameResponse> callback) {
        RetrofitWebService.getService().getWeatherByName(cityName).enqueue(callback);
    }
}
