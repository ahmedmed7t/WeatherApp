package com.medhat.weatherapp.data.Repositories;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.medhat.weatherapp.data.Model.ErrorResponse;
import com.medhat.weatherapp.data.Model.NameWeatherModels.WeatherByNameResponse;
import com.medhat.weatherapp.data.api.GetByName.GetByNameHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetByNameRepo {

    private GetByNameHelper getByNameHelper;
    private ArrayList<WeatherByNameResponse> weatherResponse = new ArrayList<>();

    @Inject
    public GetByNameRepo(GetByNameHelper getByNameHelper) {
        this.getByNameHelper = getByNameHelper;
    }

    @SuppressLint("CheckResult")
    public Single<Response<WeatherByNameResponse>> getWeatherByName(String name){
        return getByNameHelper.getWeather(name);
    }

    public ArrayList<WeatherByNameResponse> getWeatherResponse() {
        return weatherResponse;
    }
}
