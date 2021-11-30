package com.medhat.weatherapp.weatherByName.data.local;

import androidx.annotation.NonNull;

import com.medhat.weatherapp.base.models.LocalWeather;
import com.medhat.weatherapp.base.room.WeatherDao;
import com.medhat.weatherapp.savedWeather.data.models.Weather;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class GetByNameLocalDBImp implements GetByNameLocalDB {

    public WeatherDao weatherDao;

    @Inject
    public GetByNameLocalDBImp(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    @Override
    public void addItem(Weather weather) {
        LocalWeather localWeather = new LocalWeather();
        localWeather.setDateTime(weather.getDateTime());
        localWeather.setMaxTemp(weather.getMaxTemp());
        localWeather.setMinTemp(weather.getMinTemp());
        localWeather.setWindSpeed(weather.getWindSpeed());
        localWeather.setWeatherDescription(weather.getWeatherDescription());

        weatherDao.insertWeatherItem(localWeather)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleObserver<Void>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Void unused) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
       // realm.executeTransactionAsync(realm -> realm.insert(weather));
    }
}
