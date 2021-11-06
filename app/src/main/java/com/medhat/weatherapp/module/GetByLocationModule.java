package com.medhat.weatherapp.module;

import com.medhat.weatherapp.data.api.getByLocation.GetByLocationHelper;
import com.medhat.weatherapp.data.api.getByLocation.GetByLocationHelperImp;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;

@Module
@InstallIn(ViewModelComponent.class)
public class GetByLocationModule {

    @Provides
    public static GetByLocationHelper getByLocationHelper(){
        return new GetByLocationHelperImp() ;
    }
}
