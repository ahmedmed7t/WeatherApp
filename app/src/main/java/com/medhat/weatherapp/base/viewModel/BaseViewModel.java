package com.medhat.weatherapp.base.viewModel;

import androidx.lifecycle.ViewModel;

import com.medhat.weatherapp.base.repository.BaseRepository;

import javax.inject.Inject;

public class BaseViewModel<T extends BaseRepository> extends ViewModel {

    protected T repository;

    @Inject
    public BaseViewModel() {
    }
}
