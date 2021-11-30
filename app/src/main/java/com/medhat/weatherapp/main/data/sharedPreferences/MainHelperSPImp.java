package com.medhat.weatherapp.main.data.sharedPreferences;

import android.content.SharedPreferences;

import com.medhat.weatherapp.base.constants.Constants;

import javax.inject.Inject;

public class MainHelperSPImp implements MainHelperSP {

    private SharedPreferences sharedPreferences;

    @Inject
    public MainHelperSPImp(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public String getSavedLanguage() {
        return sharedPreferences.getString(Constants.LANGUAGE_KEY, Constants.ENGLISH_LANGUAGE);
    }

    @Override
    public void setDefaultLanguage(String language) {
        sharedPreferences.edit().putString(Constants.LANGUAGE_KEY,language).apply();
    }
}
