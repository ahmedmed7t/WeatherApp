package com.medhat.weatherapp.ui.GetByName;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.data.Model.LocationWeatherModels.WeatherInfo;
import com.medhat.weatherapp.data.Model.NameWeatherModels.WeatherByNameResponse;
import com.medhat.weatherapp.data.Repositories.GetByNameRepo;

import java.net.ContentHandler;
import java.util.ArrayList;

import javax.inject.Inject;

public class WeatherByNameViewModel extends ViewModel {

    private GetByNameRepo getByNameRepo;

    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<ArrayList<WeatherInfo>> liveResponseData = new MutableLiveData<>();

    private MutableLiveData<Integer> responseCount = new MutableLiveData<>(0);
    private int requestCount = 0;

    @Inject
    public WeatherByNameViewModel(GetByNameRepo getByNameRepo) {
        this.getByNameRepo = getByNameRepo;
    }

    public void getWeatherForCities(String[] cities){
        requestCount = cities.length;
        responseCount.setValue(0);
        for(String item : cities){
            getByNameRepo.getWeatherByName(item.trim(),errorMessage, responseCount);
        }
    }

    public void validateSearchValue(String searchValue, Context context) {
        String[] cities = searchValue.split(",");
        if (cities.length >= 3 && cities.length <= 7) {
            getWeatherForCities(cities);
        }else if(cities.length < 3){
            errorMessage.setValue(context.getString(R.string.minimum_number_of_cities_should_be_3));
        }else{
            errorMessage.setValue(context.getString(R.string.maximum_number_of_cities_should_be_7));
        }
    }

    public void validateResponse(){
        if(requestCount == responseCount.getValue()){
            liveResponseData.setValue(getWeatherInfo(getByNameRepo.getWeatherResponse()));
        }
    }

    public ArrayList<WeatherInfo> getWeatherInfo(ArrayList<WeatherByNameResponse> model){
        ArrayList<WeatherInfo> weatherResponse = new ArrayList<>();
        for(int i = 0 ; i < model.size() ; i ++) {
            weatherResponse.add(
                    new WeatherInfo(
                            model.get(i).getMain().getTempMin(),
                            model.get(i).getMain().getTempMax(),
                            model.get(i).getWeather().get(0).getDescription(),
                            model.get(i).getWind().getSpeed(),
                            model.get(i).getName()
                    ));
        }
        return weatherResponse;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<ArrayList<WeatherInfo>> getLiveResponse() {
        return liveResponseData;
    }

    public MutableLiveData<Integer> getResponseCount() {
        return responseCount;
    }
}
