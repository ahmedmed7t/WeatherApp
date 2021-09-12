package com.medhat.weatherapp.ui.GetByLocation;

import androidx.lifecycle.ViewModel;

import com.medhat.weatherapp.data.Repositories.GetByLocationRepo;

import javax.inject.Inject;

public class WeatherByLocationViewModel extends ViewModel {

    private GetByLocationRepo getByLocationRepo;

    @Inject
    public WeatherByLocationViewModel(GetByLocationRepo getByLocationRepo) {
        this.getByLocationRepo = getByLocationRepo;
    }
}
