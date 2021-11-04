package com.medhat.weatherapp.data.api.GetByLocation;


import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherByLocationResponse;
import com.medhat.weatherapp.data.RetrofitWebService;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class GetByLocationHelperImp implements GetByLocationHelper{

    private Response<WeatherByLocationResponse> responseCall = null;
    @Inject
    public GetByLocationHelperImp() {
    }


    @Override
    public Single<Response<WeatherByLocationResponse>> getWeather(double longitude, double latitude) {
       return RetrofitWebService.getService().getWeatherByLocation(longitude,latitude);
    }
}
