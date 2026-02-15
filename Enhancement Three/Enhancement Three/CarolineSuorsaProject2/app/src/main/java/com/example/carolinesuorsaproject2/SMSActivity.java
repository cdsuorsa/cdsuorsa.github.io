package com.example.carolinesuorsaproject2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SMSActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_activity);

        findViewById(R.id.buttonAccept).setOnClickListener(v -> requestSMSPermission());
        findViewById(R.id.buttonReject).setOnClickListener(v -> continueWithoutSMS());
    }

    private void requestSMSPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        } else {
            proceedToMain(true);
        }
    }

    private void continueWithoutSMS() {
        proceedToMain(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            proceedToMain(true);
        } else {
            proceedToMain(false);
        }
    }

    private void proceedToMain(boolean smsEnabled) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("smsEnabled", smsEnabled);
        startActivity(intent);
        finish();
    }
}
