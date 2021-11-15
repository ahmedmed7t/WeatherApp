package com.medhat.weatherapp.di;

import com.medhat.weatherapp.data.RetrofitService;
import com.medhat.weatherapp.data.api.getByLocation.GetByLocationHelper;
import com.medhat.weatherapp.data.api.getByLocation.GetByLocationHelperImp;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public class GetByLocationModule {

    @Provides
    public GetByLocationHelper getByLocationHelper(RetrofitService retrofitService){
        return new GetByLocationHelperImp(retrofitService) ;
    }
}
