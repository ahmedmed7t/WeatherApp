package com.medhat.weatherapp.weatherByLocation.data.localDB;

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

public class GetByLocationLocalDBImp implements GetByLocationLocalDB {

    public WeatherDao weatherDao;

    @Inject
    public GetByLocationLocalDBImp(WeatherDao weatherDao) {
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
        //realm.executeTransactionAsync(realm -> realm.insert(weather));
    }
}
