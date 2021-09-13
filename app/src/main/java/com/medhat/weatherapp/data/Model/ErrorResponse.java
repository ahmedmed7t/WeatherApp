package com.medhat.weatherapp.data.Model;

public class ErrorResponse {
    private int cod;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(int cod, String message) {
        this.cod = cod;
        this.message = message;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
