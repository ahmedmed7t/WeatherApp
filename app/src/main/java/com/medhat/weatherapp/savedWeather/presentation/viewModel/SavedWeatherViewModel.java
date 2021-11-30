package com.medhat.weatherapp.savedWeather.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.medhat.weatherapp.base.models.LocalWeather;
import com.medhat.weatherapp.weatherByLocation.data.models.WeatherInfo;
import com.medhat.weatherapp.savedWeather.data.repositories.SavedWeatherRepo;
import com.medhat.weatherapp.base.viewModel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SavedWeatherViewModel extends BaseViewModel<SavedWeatherRepo> {

    @Inject
    public SavedWeatherViewModel(SavedWeatherRepo savedWeatherRepo){
        repository = savedWeatherRepo;
    }

    public MutableLiveData<List<LocalWeather>> getAllSavedWeather(){
        return repository.getSavedWeather();
    }

    public ArrayList<WeatherInfo> convertLocalWeatherToInfo(List<LocalWeather> weathers){
        ArrayList<WeatherInfo> result = new ArrayList<>();
        for (LocalWeather item : weathers){
            result.add(
                    new WeatherInfo(
                            item.getMinTemp(),
                            item.getMaxTemp(),
                            item.getWeatherDescription(),
                            item.getWindSpeed(),
                            item.getDateTime()
                    )
            );
        }
        return result;
    }
}
