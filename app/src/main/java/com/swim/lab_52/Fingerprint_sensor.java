package com.swim.lab_52;

import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Fingerprint_sensor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_sensor);

        TextView textView_Status = findViewById(R.id.textView_Status_Fingerprint);
        TextView textView_Data = findViewById(R.id.textView_Data_Fingerprint);

        setTitle("Fingerprint sensor");

        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        if (fingerprintManager.isHardwareDetected()) {
            textView_Status.setText("Fingerprint scanner: detected");
            textView_Status.setTextColor(Color.parseColor("#009900"));
        }
        else {
            textView_Status.setText("Fingerprint scanner: not detected");
            textView_Status.setTextColor(Color.parseColor("#990000"));
        }
        if (fingerprintManager.hasEnrolledFingerprints()) {
            textView_Data.setText("Saved fingerprints: present");
            textView_Data.setTextColor(Color.parseColor("#009900"));
        }
        else {
            textView_Data.setText("Saved fingerprints: missing");
            textView_Data.setTextColor(Color.parseColor("#990000"));
        }
    }
}
