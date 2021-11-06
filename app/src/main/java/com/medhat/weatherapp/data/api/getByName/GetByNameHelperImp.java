package com.medhat.weatherapp.data.api.getByName;

import com.medhat.weatherapp.data.models.nameWeatherModels.WeatherByNameResponse;
import com.medhat.weatherapp.data.RetrofitWebService;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByNameHelperImp implements GetByNameHelper{

    @Inject
    public GetByNameHelperImp() {
    }

    @Override
    public Single<Response<WeatherByNameResponse>> getWeather(String cityName) {
        return RetrofitWebService.getService().getWeatherByName(cityName);
    }
}
