package com.medhat.weatherapp.data.api.GetByName;

import com.medhat.weatherapp.data.Model.NameWeatherModels.WeatherByNameResponse;
import com.medhat.weatherapp.data.RetrofitWebService;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Callback;
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
