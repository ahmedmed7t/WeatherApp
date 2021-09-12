package com.medhat.weatherapp.data.api.GetByLocation;

import com.medhat.weatherapp.data.RetrofitWebService;

import javax.inject.Inject;

public class GetByLocationHelperImp implements GetByLocationHelper{

    @Inject
    public GetByLocationHelperImp() {
    }

    @Override
    public String getWeather(Long longitude, Long latitude) {
        //RetrofitWebService.getService().
        return null;
    }
}
