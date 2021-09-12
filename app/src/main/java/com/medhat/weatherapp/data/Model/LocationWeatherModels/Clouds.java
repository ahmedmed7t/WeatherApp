
package com.medhat.weatherapp.data.Model.LocationWeatherModels;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Clouds {

    @Expose
    private Long all;

    public Long getAll() {
        return all;
    }

    public void setAll(Long all) {
        this.all = all;
    }

}
