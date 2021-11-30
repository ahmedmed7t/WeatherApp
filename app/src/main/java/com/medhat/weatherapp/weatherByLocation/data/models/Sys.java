
package com.medhat.weatherapp.weatherByLocation.data.models;

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
