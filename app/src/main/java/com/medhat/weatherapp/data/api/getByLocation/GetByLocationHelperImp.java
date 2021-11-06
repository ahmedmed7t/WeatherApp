package com.medhat.weatherapp.data.api.getByLocation;


import com.medhat.weatherapp.data.models.locationWeatherModels.WeatherByLocationResponse;
import com.medhat.weatherapp.data.RetrofitWebService;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByLocationHelperImp implements GetByLocationHelper{

    @Inject
    public GetByLocationHelperImp() {
    }

    @Override
    public Single<Response<WeatherByLocationResponse>> getWeather(double longitude, double latitude) {
       return RetrofitWebService.getService().getWeatherByLocation(longitude,latitude);
    }
}
