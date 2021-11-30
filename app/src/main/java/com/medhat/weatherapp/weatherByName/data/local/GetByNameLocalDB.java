package com.medhat.weatherapp.weatherByName.data.local;

import com.medhat.weatherapp.savedWeather.data.models.Weather;

public interface GetByNameLocalDB {
    void addItem(Weather weather);
}
