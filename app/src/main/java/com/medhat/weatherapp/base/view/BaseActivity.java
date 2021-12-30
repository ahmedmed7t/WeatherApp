package com.medhat.weatherapp.base.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.base.constants.Constants;
import com.medhat.weatherapp.base.repository.BaseRepository;
import com.medhat.weatherapp.di.AppModule;
import com.medhat.weatherapp.utils.LocalHelper;
import com.medhat.weatherapp.base.viewModel.BaseViewModel;
import com.medhat.weatherapp.main.presentation.view.MainActivity;
import com.medhat.weatherapp.weatherByName.presentation.viewModel.WeatherByNameViewModel;

import javax.inject.Inject;


public class BaseActivity <T extends BaseViewModel> extends AppCompatActivity {

    protected T viewModel;
    private SharedPreferences sharedPreferences;

    private BaseViewModel<BaseRepository> baseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        sharedPreferences = new AppModule().getSharedPreferences(this);

        baseViewModel = new ViewModelProvider(this).get(BaseViewModel.class);

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