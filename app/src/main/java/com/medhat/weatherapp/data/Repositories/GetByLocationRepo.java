package com.medhat.weatherapp.data.Repositories;

import com.medhat.weatherapp.data.api.GetByLocation.GetByLocationHelper;

import javax.inject.Inject;

public class GetByLocationRepo {

    private GetByLocationHelper locationHelper;

    @Inject
    public GetByLocationRepo(GetByLocationHelper locationHelper) {
        this.locationHelper = locationHelper;
    }

    public String getWeatherByLocation(){
        locationHelper.getWeather(Long.valueOf(4154),Long.valueOf(5242254));
        return null;
    }
}
