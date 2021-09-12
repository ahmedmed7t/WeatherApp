package com.medhat.weatherapp.module;

import com.medhat.weatherapp.ui.GetByLocation.WeatherByLocationViewModel;
import com.medhat.weatherapp.ui.GetByName.WeatherByNameViewModel;

import dagger.Component;

@Component(modules = {GetByNameModule.class, GetByLocationModule.class})
public interface GetViewModelComponent {
    WeatherByNameViewModel getWeatherByName();
    WeatherByLocationViewModel getWeatherByLocation();
}
