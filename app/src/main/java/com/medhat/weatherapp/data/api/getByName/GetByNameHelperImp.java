package com.medhat.weatherapp.data.api.getByName;

import com.medhat.weatherapp.data.RetrofitService;
import com.medhat.weatherapp.data.models.nameWeatherModels.WeatherByNameResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByNameHelperImp implements GetByNameHelper{

    RetrofitService retrofitService;
    @Inject
    public GetByNameHelperImp(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    @Override
    public Single<Response<WeatherByNameResponse>> getWeather(String cityName) {
        return retrofitService.getWeatherByName(cityName);
    }
}
