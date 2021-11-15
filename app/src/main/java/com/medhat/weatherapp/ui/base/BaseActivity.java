package com.medhat.weatherapp.ui.base;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import com.medhat.weatherapp.R;
import com.medhat.weatherapp.ui.mainActivity.MainActivity;

import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

public class BaseActivity <T extends BaseViewModel> extends AppCompatActivity {

    protected final String ARABIC_LANGUAGE = "ar";
    protected final String ENGLISH_LANGUAGE = "en";

    protected T viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    protected void changeLanguage(String language) {
        if (Build.VERSION.SDK_INT <= 25) {
            updateResource(this, language);
            updateResourcesLegacy(this, language);
        } else {
            setLocaleHighApi(language);
        }

        restart();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private Context updateResource(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);

        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }

    private void setLocaleHighApi(String langCode) {
        Locale locale;
        locale = new Locale(langCode);
        Configuration config = new Configuration(getBaseContext().getResources().getConfiguration());
        Locale.setDefault(locale);
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    private void restart(){
//        Intent intent = getIntent();
//        finish();
//        startActivity(intent);
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
//        this.finishAffinity();
    }
}