package com.medhat.weatherapp.otp;

import android.content.Intent;

public interface SmsListener {
    void messageReceived(Intent messageText);
}
