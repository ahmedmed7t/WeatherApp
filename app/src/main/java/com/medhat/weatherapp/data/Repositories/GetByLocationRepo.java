package com.medhat.weatherapp.data.Repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherByLocationResponse;
import com.medhat.weatherapp.data.api.GetByLocation.GetByLocationHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetByLocationRepo {

    private GetByLocationHelper locationHelper;

    @Inject
    public GetByLocationRepo(GetByLocationHelper locationHelper) {
        this.locationHelper = locationHelper;
    }

    public void getWeatherByLocation(double longitude, double latitude,
                                     MutableLiveData<WeatherByLocationResponse> weatherLiveData,
                                     MutableLiveData<String> errorMessage){
        locationHelper.getWeather(longitude, latitude, new Callback<WeatherByLocationResponse>() {
            @Override
            public void onResponse(Call<WeatherByLocationResponse> call, Response<WeatherByLocationResponse> response) {
                if(response.isSuccessful()){
                    weatherLiveData.setValue(response.body());
                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String error = jObjError.getJSONObject("error").getString("message");
                        errorMessage.setValue(error);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherByLocationResponse> call, Throwable t) {

            }
        });
    }


}
