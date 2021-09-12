package com.medhat.weatherapp.data.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.medhat.weatherapp.data.Model.NameWeatherModels.WeatherByNameResponse;
import com.medhat.weatherapp.data.api.GetByName.GetByNameHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetByNameRepo {

    private GetByNameHelper getByNameHelper;

    @Inject
    public GetByNameRepo(GetByNameHelper getByNameHelper) {
        this.getByNameHelper = getByNameHelper;
    }

    public void getWeatherByName(String name, MutableLiveData<String> errorMessage,
                                 MutableLiveData<ArrayList<WeatherByNameResponse>> weatherResponse){
        getByNameHelper.getWeather(name, new Callback<WeatherByNameResponse>() {
            @Override
            public void onResponse(Call<WeatherByNameResponse> call, Response<WeatherByNameResponse> response) {
                if(response.isSuccessful()){
                    weatherResponse.getValue().add(response.body());
                    weatherResponse.setValue(weatherResponse.getValue());
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
            public void onFailure(Call<WeatherByNameResponse> call, Throwable t) {

            }
        });
    }
}
