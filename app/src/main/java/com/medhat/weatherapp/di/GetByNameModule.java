package com.medhat.weatherapp.di;

import com.medhat.weatherapp.main.data.sharedPreferences.MainHelperSP;
import com.medhat.weatherapp.main.data.sharedPreferences.MainHelperSPImp;
import com.medhat.weatherapp.weatherByName.data.api.GetByNameHelper;
import com.medhat.weatherapp.weatherByName.data.api.GetByNameHelperImp;
import com.medhat.weatherapp.weatherByName.data.local.GetByNameLocalDB;
import com.medhat.weatherapp.weatherByName.data.local.GetByNameLocalDBImp;
import com.medhat.weatherapp.savedWeather.data.localDB.SavedWeatherHelper;
import com.medhat.weatherapp.savedWeather.data.localDB.SavedWeatherHelperImp;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class GetByNameModule {

    @Binds
    public abstract GetByNameHelper getByNameHelper(GetByNameHelperImp getByNameHelperImp);

    @Binds
    public abstract GetByNameLocalDB getByNameLocalHelper(GetByNameLocalDBImp getByNameLocalDBImp);

    @Binds
    public abstract SavedWeatherHelper savedWeatherLocalHelper(SavedWeatherHelperImp savedWeatherHelperImp);

}
