package com.medhat.weatherapp.weatherByLocation.data.models;


public class WeatherInfo  {
    private double minTemp;
    private double maxTemp;

    private String weatherDescription;

    private String dateTime;

    private double windSpeed;

    public WeatherInfo() {
    }

    public WeatherInfo(double minTemp, double maxTemp, String weatherDescription,
                       double windSpeed, String dateTime) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.weatherDescription = weatherDescription;
        this.windSpeed = windSpeed;
        this.dateTime = dateTime;
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

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


}
