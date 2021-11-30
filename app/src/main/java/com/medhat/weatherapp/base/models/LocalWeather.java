package com.medhat.weatherapp.base.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class LocalWeather {

    @PrimaryKey(autoGenerate = true)
    private int id ;

    @ColumnInfo(name = "min_temp")
    private double minTemp;

    @ColumnInfo(name = "max_temp")
    private double maxTemp;

    @ColumnInfo(name = "weather_description")
    private String weatherDescription;

    @ColumnInfo(name = "date_time")
    private String dateTime;

    @ColumnInfo(name = "wind_speed")
    private double windSpeed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
