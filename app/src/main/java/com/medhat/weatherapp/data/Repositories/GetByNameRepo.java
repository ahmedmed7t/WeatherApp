package com.medhat.weatherapp.data.Repositories;

import com.medhat.weatherapp.data.api.GetByName.GetByNameHelper;

import javax.inject.Inject;

public class GetByNameRepo {

    private GetByNameHelper getByNameHelper;

    @Inject
    public GetByNameRepo(GetByNameHelper getByNameHelper) {
        this.getByNameHelper = getByNameHelper;
    }

    public void getWeatherByName(String name){
        getByNameHelper.getByName(name);
    }
}
