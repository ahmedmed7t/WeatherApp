package com.medhat.weatherapp.data.localDB.getByNameLocal;

import com.medhat.weatherapp.data.localDB.localModels.Weather;

import javax.inject.Inject;

import io.realm.Realm;

public class GetByNameLocalDBImp implements GetByNameLocalDB {

    public Realm realm;

    @Inject
    public GetByNameLocalDBImp(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void addItem(Weather weather) {
        realm.executeTransaction(realm -> realm.insert(weather));
    }
}
