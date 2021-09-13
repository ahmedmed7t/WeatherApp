package com.medhat.weatherapp.data.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.medhat.weatherapp.data.Model.ErrorResponse;
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
    private ArrayList<WeatherByNameResponse> weatherResponse = new ArrayList<>();

    @Inject
    public GetByNameRepo(GetByNameHelper getByNameHelper) {
        this.getByNameHelper = getByNameHelper;
    }

    public void getWeatherByName(String name, MutableLiveData<String> errorMessage,
                                  MutableLiveData<Integer> count){
        getByNameHelper.getWeather(name, new Callback<WeatherByNameResponse>() {
            @Override
            public void onResponse(Call<WeatherByNameResponse> call, Response<WeatherByNameResponse> response) {
                if(response.isSuccessful()){
                    weatherResponse.add(response.body());
                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ErrorResponse errorResponse = new Gson().fromJson(String.valueOf(jObjError), ErrorResponse.class);
                        errorMessage.setValue(name + " " + errorResponse.getMessage());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
                count.setValue(count.getValue()+1);
            }

            @Override
            public void onFailure(Call<WeatherByNameResponse> call, Throwable t) {
                count.setValue(count.getValue()+1);
            }
        });
    }

    public ArrayList<WeatherByNameResponse> getWeatherResponse() {
        return weatherResponse;
    }
}
