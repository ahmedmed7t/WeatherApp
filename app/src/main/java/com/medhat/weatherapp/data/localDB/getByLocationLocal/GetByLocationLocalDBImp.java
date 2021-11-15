package com.medhat.weatherapp.data.localDB.getByLocationLocal;

import com.medhat.weatherapp.data.localDB.localModels.Weather;

import javax.inject.Inject;

import io.realm.Realm;

public class GetByLocationLocalDBImp implements GetByLocationLocalDB {

    public Realm realm;

    @Inject
    public GetByLocationLocalDBImp(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void addItem(Weather weather) {
        realm.executeTransaction(realm -> realm.insert(weather));
    }
}
