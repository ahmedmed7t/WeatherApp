package com.medhat.weatherapp.ui.base;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class BaseViewModel extends ViewModel {

    @Inject
    public BaseViewModel() {
    }
}
