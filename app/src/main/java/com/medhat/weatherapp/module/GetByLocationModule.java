package com.medhat.weatherapp.module;

import com.medhat.weatherapp.data.api.GetByLocation.GetByLocationHelper;
import com.medhat.weatherapp.data.api.GetByLocation.GetByLocationHelperImp;

import dagger.Module;
import dagger.Provides;

@Module
public interface GetByLocationModule {
    @Provides
    static GetByLocationHelper getByLocationHelper(){
        return new GetByLocationHelperImp();
    }
}
