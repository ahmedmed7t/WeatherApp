package com.medhat.weatherapp.ui.GetByLocation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.medhat.weatherapp.data.Model.LocationWeatherModels.Weather;
import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherByLocationResponse;
import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherInfo;
import com.medhat.weatherapp.data.Repositories.GetByLocationRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Response;

public class WeatherByLocationViewModel extends ViewModel {

    private GetByLocationRepo getByLocationRepo;

    private MutableLiveData<ArrayList<WeatherInfo>> weatherData = new MutableLiveData<>();
    private MutableLiveData<WeatherByLocationResponse> weatherResponse = new MutableLiveData<>();

    @Inject
    public WeatherByLocationViewModel(GetByLocationRepo getByLocationRepo) {
        this.getByLocationRepo = getByLocationRepo;
    }

    public void getWeatherByLocation(double longitude, double latitude){
        Log.v("nnnnnnnnnnnnnn","done with lenght view model");
        getByLocationRepo.getWeatherByLocation(longitude,latitude);
        weatherResponse = getByLocationRepo.getWeatherLiveData();
        Log.v("nnnnnnnnnnnnnn","done with lenght view model");

    }

    public void get5DaysWeather(WeatherByLocationResponse model){
        ArrayList<WeatherInfo> weatherResponse = new ArrayList<>();
        for(int i = 0 ; i < model.getList().size() ; i ++){
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

    public MutableLiveData<ArrayList<WeatherInfo>> getWeatherData() {
        return weatherData;
    }

    public MutableLiveData<WeatherByLocationResponse> getWeatherResponse() {
        return weatherResponse;
    }
}
