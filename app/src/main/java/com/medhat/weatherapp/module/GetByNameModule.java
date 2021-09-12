package com.medhat.weatherapp.module;

import com.medhat.weatherapp.data.api.GetByName.GetByNameHelper;
import com.medhat.weatherapp.data.api.GetByName.GetByNameHelperImp;

import dagger.Module;
import dagger.Provides;

@Module
public interface GetByNameModule {

    @Provides
    static GetByNameHelper getByNameHelper(){
        return new GetByNameHelperImp();
    }
}
