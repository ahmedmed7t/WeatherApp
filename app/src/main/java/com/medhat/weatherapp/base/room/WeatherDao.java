package com.medhat.weatherapp.base.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.medhat.weatherapp.base.models.LocalWeather;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM localweather")
    Single<List<LocalWeather>> getAllWeather();

    @Insert
    Single<Void> insertWeatherItem(LocalWeather weather);

    @Delete
    void delete(LocalWeather weather);
}
