package com.medhat.weatherapp.data.Repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherByLocationResponse;
import com.medhat.weatherapp.data.api.GetByLocation.GetByLocationHelper;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetByLocationRepo {

    private GetByLocationHelper locationHelper;

    MutableLiveData<WeatherByLocationResponse> weatherLiveData = new MutableLiveData<>();

    @Inject
    public GetByLocationRepo(GetByLocationHelper locationHelper) {
        this.locationHelper = locationHelper;
    }

    public MutableLiveData<WeatherByLocationResponse> getWeatherByLocation(double longitude, double latitude){
        locationHelper.getWeather(longitude, latitude, new Callback<WeatherByLocationResponse>() {
            @Override
            public void onResponse(Call<WeatherByLocationResponse> call, Response<WeatherByLocationResponse> response) {
                if(response.isSuccessful()){
                    weatherLiveData.setValue(response.body());
                }else{
                    try {
                        Log.v("nnnnnnnnnnnnnn","noo done in repo"+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<WeatherByLocationResponse> call, Throwable t) {

            }
        });

        return weatherLiveData;
    }


    public MutableLiveData<WeatherByLocationResponse> getWeatherLiveData() {
        return weatherLiveData;
    }
}
