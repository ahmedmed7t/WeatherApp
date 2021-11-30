package com.medhat.weatherapp.base.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.base.constants.Constants;
import com.medhat.weatherapp.utils.LocalHelper;
import com.medhat.weatherapp.base.viewModel.BaseViewModel;
import com.medhat.weatherapp.main.presentation.view.MainActivity;

import javax.inject.Inject;

public class BaseActivity <T extends BaseViewModel> extends AppCompatActivity {

    protected T viewModel;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        checkLanguage();
    }

    private void checkLanguage(){
        String language = sharedPreferences.getString(Constants.LANGUAGE_KEY,Constants.ENGLISH_LANGUAGE);
        LocalHelper.updateBaseContextLocale(this, language);
    }

    protected void changeLanguage( String language){
        LocalHelper.updateBaseContextLocale(this, language);
        restart();
    }

    private void restart(){
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}