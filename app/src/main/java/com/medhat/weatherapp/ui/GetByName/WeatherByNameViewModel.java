package com.medhat.weatherapp.ui.GetByName;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.medhat.weatherapp.R;
import com.medhat.weatherapp.data.Model.ErrorResponse;
import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherInfo;
import com.medhat.weatherapp.data.Model.NameWeatherModels.WeatherByNameResponse;
import com.medhat.weatherapp.data.Repositories.GetByNameRepo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherByNameViewModel extends ViewModel {

    private GetByNameRepo getByNameRepo;

    private MutableLiveData<Object> errorMessage = new MutableLiveData<>();
    private MutableLiveData<ArrayList<WeatherInfo>> liveResponseData = new MutableLiveData<>();

    private ArrayList<WeatherInfo> weatherInfos = new ArrayList<>();
    private int requestCount = 0;

    @Inject
    public WeatherByNameViewModel(GetByNameRepo getByNameRepo) {
        this.getByNameRepo = getByNameRepo;
    }

    @SuppressLint("CheckResult")
    public void getWeatherForCities(String[] cities) {
        requestCount = cities.length;
        for (String item : cities) {
            getByNameRepo.getWeatherByName(item)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(weatherByNameResponse -> {
                                if (weatherByNameResponse.isSuccessful()) {
                                    weatherInfos.add(getWeatherInfo(weatherByNameResponse.body()));
                                } else {
                                    try {
                                        JSONObject jObjError = new JSONObject(weatherByNameResponse.errorBody().string());
                                        ErrorResponse errorResponse = new Gson().fromJson(String.valueOf(jObjError), ErrorResponse.class);
                                        errorMessage.setValue(item + " " + errorResponse.getMessage());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                requestCount--;
                                validateResponse();
                            }, throwable -> {
                                requestCount--;
                                validateResponse();
                            }
                    );
        }
    }

    public void validateSearchValue(String searchValue) {
        String[] cities = searchValue.split(",");
        if (cities.length >= 3 && cities.length <= 7) {
            getWeatherForCities(cities);
        }else if(cities.length < 3){
            errorMessage.setValue(R.string.minimum_number_of_cities_should_be_3);
        }else{
            errorMessage.setValue(R.string.maximum_number_of_cities_should_be_7);
        }
    }

    public void validateResponse(){
        if(requestCount == 0){
            liveResponseData.setValue(weatherInfos);
        }
    }

    public WeatherInfo getWeatherInfo(WeatherByNameResponse model) {
        return new WeatherInfo(
                model.getMain().getTempMin(),
                model.getMain().getTempMax(),
                model.getWeather().get(0).getDescription(),
                model.getWind().getSpeed(),
                model.getName()
        );
    }

    public MutableLiveData<Object> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<ArrayList<WeatherInfo>> getLiveResponse() {
        return liveResponseData;
    }
}
