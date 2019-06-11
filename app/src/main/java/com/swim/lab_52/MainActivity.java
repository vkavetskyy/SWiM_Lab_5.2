package com.swim.lab_52;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        boolean enabled = !sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty();

        // Accelerometer
        Button button = findViewById(R.id.button_Accelerometer);
        TextView textView = findViewById(R.id.textView_Accelerometer);
        textView.setVisibility(View.VISIBLE);
        textView.setText(getString(enabled ? R.string.enabled : R.string.disabled));
        textView.setTextColor(enabled ? Color.parseColor("#009900") : Color.parseColor("#990000"));
        if(!enabled) button.setEnabled(false);

        // Barometer/Pressure
        button = findViewById(R.id.button_Barometer);
        enabled = !sensorManager.getSensorList(Sensor.TYPE_PRESSURE).isEmpty();
        textView = findViewById(R.id.textView_Barometer);
        textView.setText(getString(enabled ? R.string.enabled : R.string.disabled));
        textView.setTextColor(enabled ? Color.parseColor("#009900") : Color.parseColor("#990000"));
        if(!enabled) button.setEnabled(false);

        // Fingerprint sensor/scanner
        button = findViewById(R.id.button_Fingerprint_Sensor);
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        enabled = fingerprintManager.isHardwareDetected();
        textView = findViewById(R.id.textView_Fingerprint_Status);
        textView.setText(getString(enabled ? R.string.enabled : R.string.disabled));
        textView.setTextColor(enabled ? Color.parseColor("#009900") : Color.parseColor("#990000"));
        if(!enabled) button.setEnabled(false);

        //Geomagnetic sensor/Magnetic field sensor
        button = findViewById(R.id.button_Geomagnetic_Sensor);
        enabled = !sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).isEmpty();
        textView = findViewById(R.id.textView_Geomagnetic_Sensor);
        textView.setText(getString(enabled ? R.string.enabled : R.string.disabled));
        textView.setTextColor(enabled ? Color.parseColor("#009900") : Color.parseColor("#990000"));
        if(!enabled) button.setEnabled(false);

        // Gyroscope
        button = findViewById(R.id.button_Gyroscope);
        enabled = !sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE).isEmpty();
        textView = findViewById(R.id.textView_Gyroscope);
        textView.setText(getString(enabled ? R.string.enabled : R.string.disabled));
        textView.setTextColor(enabled ? Color.parseColor("#009900") : Color.parseColor("#990000"));
        if(!enabled) button.setEnabled(false);

        // Light sensor
        button = findViewById(R.id.button_Light_Sensor);
        enabled = !sensorManager.getSensorList(Sensor.TYPE_LIGHT).isEmpty();
        textView = findViewById(R.id.textView_Light_Sensor);
        textView.setText(getString(enabled ? R.string.enabled : R.string.disabled));
        textView.setTextColor(enabled ? Color.parseColor("#009900") : Color.parseColor("#990000"));
        if(!enabled) button.setEnabled(false);

        // Proximity sensor
        button = findViewById(R.id.button_Proximity_Sensor);
        enabled = !sensorManager.getSensorList(Sensor.TYPE_PROXIMITY).isEmpty();
        textView = findViewById(R.id.textView_Proximity_Sensor);
        textView.setText(getString(enabled ? R.string.enabled : R.string.disabled));
        textView.setTextColor(enabled ? Color.parseColor("#009900") : Color.parseColor("#990000"));
        if(!enabled) button.setEnabled(false);

        button = findViewById(R.id.button_Accelerometer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSensorsActivity(v);
            }
        });

        findViewById(R.id.button_Barometer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSensorsActivity(v);
            }
        });

        findViewById(R.id.button_Fingerprint_Sensor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSensorsActivity(v);
            }
        });

        findViewById(R.id.button_Geomagnetic_Sensor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSensorsActivity(v);
            }
        });

        findViewById(R.id.button_Gyroscope).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSensorsActivity(v);
            }
        });

        findViewById(R.id.button_Light_Sensor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSensorsActivity(v);
            }
        });

        findViewById(R.id.button_Proximity_Sensor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSensorsActivity(v);
            }
        });

        findViewById(R.id.button_Compass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSensorsActivity(v);
            }
        });
    }

    private void startSensorsActivity(final View v) {
        Intent intent = null;
        if (v.getId() == R.id.button_Accelerometer) intent = new Intent(this, Sensors.class).putExtra("sensorType", Sensor.TYPE_ACCELEROMETER);
        else if (v.getId() == R.id.button_Barometer) intent = new Intent(this, Sensors.class).putExtra("sensorType", Sensor.TYPE_PRESSURE);
        else if (v.getId() == R.id.button_Fingerprint_Sensor) intent = new Intent(this, Fingerprint_sensor.class);
        else if (v.getId() == R.id.button_Geomagnetic_Sensor) intent = new Intent(this, Sensors.class).putExtra("sensorType", Sensor.TYPE_MAGNETIC_FIELD);
        else if (v.getId() == R.id.button_Gyroscope) intent = new Intent(this, Sensors.class).putExtra("sensorType", Sensor.TYPE_GYROSCOPE);
        else if (v.getId() == R.id.button_Light_Sensor) intent = new Intent(this, Sensors.class).putExtra("sensorType", Sensor.TYPE_LIGHT);
        else if (v.getId() == R.id.button_Proximity_Sensor) intent = new Intent(this, Sensors.class).putExtra("sensorType", Sensor.TYPE_PROXIMITY);
        else if (v.getId() == R.id.button_Compass) intent = new Intent(this, Sensors.class).putExtra("sensorType", Sensor.TYPE_ORIENTATION);
        startActivity(intent);
    }
}
