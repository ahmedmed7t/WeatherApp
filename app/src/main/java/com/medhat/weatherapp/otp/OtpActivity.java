package com.medhat.weatherapp.otp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.medhat.weatherapp.OtpBroadCastReceiver;
import com.medhat.weatherapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtpActivity extends AppCompatActivity {

    private static final int REQ_USER_CONSENT = 50;

    private static final int CREDENTIAL_PICKER_REQUEST = 1;

    private static final int SMS_CONSENT_REQUEST = 2;  // Set to an unused request code
    private final OtpBroadCastReceiver smsVerificationReceiver = new OtpBroadCastReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        //requestHint();
        startListeningToSmsService();

    }


    private void requestHint() {
        try {
            HintRequest hintRequest = new HintRequest.Builder()
                    .setPhoneNumberIdentifierSupported(true)
                    .build();
            PendingIntent intent = Credentials.getClient(this).getHintPickerIntent(hintRequest);
            startIntentSenderForResult(intent.getIntentSender(),
                    CREDENTIAL_PICKER_REQUEST, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException intentException) {
            //Timber.e(intentException);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CREDENTIAL_PICKER_REQUEST:
                if (resultCode == RESULT_OK) {
                    Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                }
                break;
            case SMS_CONSENT_REQUEST:
                if (resultCode == RESULT_OK) {
                    String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                    Pattern pattern = Pattern.compile("-?\\d+");
                    Matcher matcher = pattern.matcher(message);
                    if (matcher.find()) {
                        String code = matcher.group();
                        Toast.makeText(this,code,Toast.LENGTH_LONG).show();

                    }
                }
                break;
        }

    }


    public void startListeningToSmsService() {
        SmsRetriever.getClient(this).startSmsUserConsent(null);
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        smsVerificationReceiver.setSmsVerificationCallback(new SmsListener() {
            @Override
            public void messageReceived(Intent consentIntent) {
                startActivityForResult(consentIntent, SMS_CONSENT_REQUEST);
            }
        });
        registerReceiver(smsVerificationReceiver, intentFilter,
                SmsRetriever.SEND_PERMISSION, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsVerificationReceiver);
    }
}