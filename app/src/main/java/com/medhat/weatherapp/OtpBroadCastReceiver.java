package com.medhat.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.medhat.weatherapp.otp.SmsListener;

import java.util.Objects;

public class OtpBroadCastReceiver extends BroadcastReceiver {
    private SmsListener smsVerificationCallback;

    public void setSmsVerificationCallback(SmsListener smsVerificationCallback) {
        this.smsVerificationCallback = smsVerificationCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            Status smsRetrieverStatus = (Status) extras.get(SmsRetriever.EXTRA_STATUS);
            switch (Objects.requireNonNull(smsRetrieverStatus).getStatusCode()) {
                case CommonStatusCodes.SUCCESS:
                    Intent consentIntent = extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
                    smsVerificationCallback.messageReceived(consentIntent);
                    break;
                case CommonStatusCodes.TIMEOUT:
                    break;
            }
        }
    }
}
