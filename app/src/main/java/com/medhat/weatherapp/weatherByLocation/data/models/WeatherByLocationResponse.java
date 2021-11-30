
package com.medhat.weatherapp.weatherByLocation.data.models;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class WeatherByLocationResponse {

    @Expose
    private City city;
    @Expose
    private Long cnt;
    @Expose
    private String cod;
    @Expose
    private java.util.List<List> list;
    @Expose
    private Long message;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getCnt() {
        return cnt;
    }

    public void setCnt(Long cnt) {
        this.cnt = cnt;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public Long getMessage() {
        return message;
    }

    public void setMessage(Long message) {
        this.message = message;
    }

}
