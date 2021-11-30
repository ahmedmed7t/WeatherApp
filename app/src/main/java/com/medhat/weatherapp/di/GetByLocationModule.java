package com.medhat.weatherapp.di;

import com.medhat.weatherapp.base.retrofit.RetrofitService;
import com.medhat.weatherapp.base.room.WeatherDao;
import com.medhat.weatherapp.weatherByLocation.data.api.GetByLocationHelper;
import com.medhat.weatherapp.weatherByLocation.data.api.GetByLocationHelperImp;
import com.medhat.weatherapp.weatherByLocation.data.localDB.GetByLocationLocalDB;
import com.medhat.weatherapp.weatherByLocation.data.localDB.GetByLocationLocalDBImp;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import io.realm.Realm;

@Module
@InstallIn(ViewModelComponent.class)
public class GetByLocationModule {

    @Provides
    public GetByLocationHelper getByLocationHelper(RetrofitService retrofitService){
        return new GetByLocationHelperImp(retrofitService) ;
    }

    @Provides
    public GetByLocationLocalDB getByLocationLocalHelper(WeatherDao weatherDao){
        return new GetByLocationLocalDBImp(weatherDao) ;
    }
}
