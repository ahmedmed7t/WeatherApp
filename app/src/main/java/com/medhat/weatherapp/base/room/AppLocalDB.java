package com.medhat.weatherapp.base.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.medhat.weatherapp.base.models.LocalWeather;

@Database(entities = {LocalWeather.class}, version = 1)
public abstract class AppLocalDB extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
