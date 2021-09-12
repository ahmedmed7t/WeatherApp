package com.medhat.weatherapp.ui.GetByName;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.medhat.weatherapp.data.Model.LocationWeatherModels.List;
import com.medhat.weatherapp.data.Model.NameWeatherModels.WeatherByNameResponse;
import com.medhat.weatherapp.data.Repositories.GetByNameRepo;
import com.medhat.weatherapp.data.api.GetByName.GetByNameHelper;

import java.util.ArrayList;

import javax.inject.Inject;

public class WeatherByNameViewModel extends ViewModel {

    private GetByNameRepo getByNameRepo;

    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<ArrayList<WeatherByNameResponse>> liveResponse = new MutableLiveData<>();

    @Inject
    public WeatherByNameViewModel(GetByNameRepo getByNameRepo) {
        this.getByNameRepo = getByNameRepo;
    }

    public void getWeatherForCities(String[] cities){
        for(String item : cities){
            getByNameRepo.getWeatherByName(item,errorMessage,liveResponse);
        }
    }

    public void validateSearchValue(String searchValue) {
        String[] cities = searchValue.split("/,");
        if (cities.length >= 3 && cities.length <= 7) {
            getWeatherForCities(cities);
        }else if(cities.length < 3){
            errorMessage.setValue("minimum number of cities should be 3");
        }else{
            errorMessage.setValue("maximum number of cities should be 7");
        }
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
