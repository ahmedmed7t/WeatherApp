package com.medhat.weatherapp.weatherByLocation.presentation.viewModel;


import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.medhat.weatherapp.savedWeather.data.models.Weather;
import com.medhat.weatherapp.base.models.ErrorResponse;
import com.medhat.weatherapp.weatherByLocation.data.models.WeatherByLocationResponse;
import com.medhat.weatherapp.weatherByLocation.data.models.WeatherInfo;
import com.medhat.weatherapp.weatherByLocation.data.repositories.GetByLocationRepo;
import com.medhat.weatherapp.base.viewModel.BaseViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class WeatherByLocationViewModel extends BaseViewModel<GetByLocationRepo> {


    private MutableLiveData<ArrayList<WeatherInfo>> weatherData = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    @Inject
    public WeatherByLocationViewModel(GetByLocationRepo getByLocationRepo) {
        repository = getByLocationRepo;
    }

    @SuppressLint("CheckResult")
    public void getWeatherByLocation(double longitude, double latitude) {
        repository.getWeatherByLocation(longitude, latitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherByLocationResponse -> {
                            if (weatherByLocationResponse.isSuccessful()) {
                                get5DaysWeather(weatherByLocationResponse.body());
                            } else {
                                try {
                                    JSONObject jObjError = new JSONObject(weatherByLocationResponse.errorBody().string());
                                    ErrorResponse errorResponse = new Gson().fromJson(String.valueOf(jObjError), ErrorResponse.class);
                                    errorMessage.setValue(errorResponse.getMessage());
                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        },
                        e -> {

                        }
                );
    }

    public void get5DaysWeather(WeatherByLocationResponse model) {
        ArrayList<WeatherInfo> weatherResponse = new ArrayList<>();
        for (int i = 0; i < model.getList().size(); i++) {
            weatherResponse.add(
                    new WeatherInfo(
                            model.getList().get(i).getMain().getTempMin(),
                            model.getList().get(i).getMain().getTempMax(),
                            model.getList().get(i).getWeather().get(0).getDescription(),
                            model.getList().get(i).getWind().getSpeed(),
                            model.getList().get(i).getDtTxt()
                    ));
        }
        weatherData.setValue(weatherResponse);
    }

    public void saveWeatherRecord(WeatherInfo weatherInfo){
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

    public MutableLiveData<ArrayList<WeatherInfo>> getWeatherData() {
        return weatherData;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
