package com.medhat.weatherapp.main.presentation.viewModel;

import com.medhat.weatherapp.base.viewModel.BaseViewModel;
import com.medhat.weatherapp.main.data.repositories.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends BaseViewModel<MainRepository> {

    @Inject
    public MainViewModel( MainRepository mainRepository){
        repository = mainRepository;
    }

    public String getDefaultLanguage(){
        return repository.getDefaultLanguage();
    }

    public void setDefaultLanguage(String language){
        repository.setDefaultLanguage(language);
    }
}
