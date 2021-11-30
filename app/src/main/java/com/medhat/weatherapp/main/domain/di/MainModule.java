package com.medhat.weatherapp.main.domain.di;

import com.medhat.weatherapp.main.data.sharedPreferences.MainHelperSP;
import com.medhat.weatherapp.main.data.sharedPreferences.MainHelperSPImp;
import com.medhat.weatherapp.weatherByName.data.api.GetByNameHelperImp;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class MainModule {
    @Binds
    public abstract MainHelperSP getMainHelper(MainHelperSPImp mainHelperSPImp);
}
