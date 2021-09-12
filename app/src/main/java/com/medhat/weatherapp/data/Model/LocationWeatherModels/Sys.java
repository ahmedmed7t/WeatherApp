
package com.medhat.weatherapp.data.Model.LocationWeatherModels;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Sys {

    @Expose
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

}
