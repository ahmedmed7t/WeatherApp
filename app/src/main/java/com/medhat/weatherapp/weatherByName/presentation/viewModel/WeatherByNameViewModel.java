package com.medhat.weatherapp.weatherByName.presentation.viewModel;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.medhat.weatherapp.R;
import com.medhat.weatherapp.savedWeather.data.models.Weather;
import com.medhat.weatherapp.base.models.ErrorResponse;
import com.medhat.weatherapp.weatherByLocation.data.models.WeatherInfo;
import com.medhat.weatherapp.weatherByName.data.models.WeatherByNameResponse;
import com.medhat.weatherapp.weatherByName.data.repositories.GetByNameRepo;
import com.medhat.weatherapp.base.viewModel.BaseViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class WeatherByNameViewModel extends BaseViewModel<GetByNameRepo> {


    private MutableLiveData<Object> errorMessage = new MutableLiveData<>();
    private MutableLiveData<ArrayList<WeatherInfo>> liveResponseData = new MutableLiveData<>();

    private ArrayList<WeatherInfo> weatherInfos = new ArrayList<>();
    private int requestCount = 0;

    @Inject
    public WeatherByNameViewModel(GetByNameRepo getByNameRepo) {
        repository = getByNameRepo;
    }

    public void saveWeatherItem(WeatherInfo weatherInfo) {
        repository.saveWeatherRecord(
                new Weather(
                        weatherInfo.getMinTemp(),
                        weatherInfo.getMaxTemp(),
                        weatherInfo.getWeatherDescription(),
                        weatherInfo.getWindSpeed(),
                        weatherInfo.getDateTime()
                )
        );
    }

    @SuppressLint("CheckResult")
    public void getWeatherForCities(String[] cities) {
        requestCount = cities.length;
        for (String item : cities) {
            repository.getWeatherByName(item)
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
