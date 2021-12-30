package com.medhat.weatherapp.main.presentation.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.base.constants.Constants;
import com.medhat.weatherapp.cameraScreen.CameraActivity;
import com.medhat.weatherapp.databinding.ActivityMainBinding;
import com.medhat.weatherapp.base.view.BaseActivity;
import com.medhat.weatherapp.main.domain.handlers.MainHandler;
import com.medhat.weatherapp.main.presentation.viewModel.MainViewModel;
import com.medhat.weatherapp.otp.OtpActivity;
import com.medhat.weatherapp.weatherByLocation.presentation.view.GetWeatherByLocationActivity;
import com.medhat.weatherapp.weatherByName.presentation.view.GetWeatherByNameActivity;

import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<MainViewModel> implements MainHandler {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // For View Binding
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        binding.LandingPageGetByNameCardView.setOnClickListener(v ->{
//
//        });

        // For Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(this);


    }

    @Override
    public void gotoLocationPage() {
        Intent intent = new Intent(MainActivity.this, OtpActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoNamePage() {
        Intent intent = new Intent(MainActivity.this, GetWeatherByNameActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoSavedPage() {
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        intent.putExtra("BitmapImage", "image");
        startActivity(intent);
    }

    @Override
    public void changeLanguageClicked() {
        if(viewModel.getDefaultLanguage().equals(Constants.ENGLISH_LANGUAGE)){
            changeLanguage(Constants.ARABIC_LANGUAGE);
            viewModel.setDefaultLanguage(Constants.ARABIC_LANGUAGE);
        }else {
            changeLanguage(Constants.ENGLISH_LANGUAGE);
            viewModel.setDefaultLanguage(Constants.ENGLISH_LANGUAGE);
        }
    }


}