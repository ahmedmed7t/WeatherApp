package com.medhat.weatherapp.di;

import com.medhat.weatherapp.data.api.getByName.GetByNameHelper;
import com.medhat.weatherapp.data.api.getByName.GetByNameHelperImp;
import com.medhat.weatherapp.data.localDB.getByNameLocal.GetByNameLocalDB;
import com.medhat.weatherapp.data.localDB.getByNameLocal.GetByNameLocalDBImp;

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
    public abstract GetByNameLocalDB GetByNameLocalHelper(GetByNameLocalDBImp getByNameLocalDBImp);
}
