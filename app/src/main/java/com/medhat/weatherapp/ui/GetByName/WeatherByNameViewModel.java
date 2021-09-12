package com.medhat.weatherapp.ui.GetByName;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.medhat.weatherapp.data.api.GetByName.GetByNameHelper;

import javax.inject.Inject;

public class WeatherByNameViewModel extends ViewModel {

    private GetByNameHelper getByNameHelper;

    @Inject
    public WeatherByNameViewModel(GetByNameHelper getByNameHelper) {
        this.getByNameHelper = getByNameHelper;
    }

}
