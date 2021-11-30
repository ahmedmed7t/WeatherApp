
package com.medhat.weatherapp.weatherByLocation.data.models;


@SuppressWarnings("unused")
public class Wind {

    private Long deg;
    private Double gust;
    private Double speed;

    public Long getDeg() {
        return deg;
    }

    public void setDeg(Long deg) {
        this.deg = deg;
    }

    public Double getGust() {
        return gust;
    }

    public void setGust(Double gust) {
        this.gust = gust;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

}
