package com.medhat.weatherapp.di;

import com.medhat.weatherapp.data.api.getByName.GetByNameHelper;
import com.medhat.weatherapp.data.api.getByName.GetByNameHelperImp;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class GetByNameModule {

    @Binds
    public abstract GetByNameHelper getByNameHelper(GetByNameHelperImp getByNameHelperImp);
}
