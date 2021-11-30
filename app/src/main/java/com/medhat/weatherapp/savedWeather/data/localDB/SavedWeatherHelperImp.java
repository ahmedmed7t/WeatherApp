package com.medhat.weatherapp.savedWeather.data.localDB;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.medhat.weatherapp.base.models.LocalWeather;
import com.medhat.weatherapp.base.room.WeatherDao;
import com.medhat.weatherapp.savedWeather.data.models.Weather;
import com.medhat.weatherapp.weatherByLocation.data.models.WeatherInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class SavedWeatherHelperImp implements SavedWeatherHelper {

    private WeatherDao weatherDao;

    @Inject
    public SavedWeatherHelperImp(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    @Override
    public MutableLiveData<List<LocalWeather>> getAllWeather() {

        MutableLiveData<List<LocalWeather>> result = new MutableLiveData<>();
        weatherDao.getAllWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<LocalWeather>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<LocalWeather> weathers) {
                result.setValue(weathers);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });


//        realm.executeTransactionAsync( realm1 -> {
//            RealmResults<Weather> list;
//            ArrayList<WeatherInfo> records = new ArrayList<>();
//            list = realm1.where(Weather.class).findAll();
//            for (Weather weather : list)
//                records.add(
//                        new WeatherInfo(
//                                weather.getMinTemp(),
//                                weather.getMaxTemp(),
//                                weather.getWeatherDescription(),
//                                weather.getWindSpeed(),
//                                weather.getDateTime()
//                        )
//                );
//            result.postValue(records);
//        });

        return result;
    }
}
