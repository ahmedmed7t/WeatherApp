package com.medhat.weatherapp.main.data.repositories;

import com.medhat.weatherapp.base.repository.BaseRepository;
import com.medhat.weatherapp.main.data.sharedPreferences.MainHelperSP;

import javax.inject.Inject;

public class MainRepository extends BaseRepository {

    private MainHelperSP mainHelperSP;

    @Inject
    public MainRepository(MainHelperSP mainHelperSP){
        this.mainHelperSP = mainHelperSP;
    }

    public String getDefaultLanguage(){
        return mainHelperSP.getSavedLanguage();
    }

    public void setDefaultLanguage(String language){
        mainHelperSP.setDefaultLanguage(language);
    }
}
