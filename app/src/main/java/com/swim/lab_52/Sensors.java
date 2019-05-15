package com.swim.lab_52;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Sensors extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager = null;
    private int sensorType;
    private float currentDegree = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorType = getIntent().getIntExtra("sensorType", Sensor.TYPE_ACCELEROMETER);
        if (sensorType == Sensor.TYPE_ACCELEROMETER) setTitle(R.string.text_Accelerometer);
        else if (sensorType == Sensor.TYPE_PRESSURE) setTitle(R.string.text_Barometer);
        else if (sensorType == Sensor.TYPE_MAGNETIC_FIELD) setTitle(R.string.text_Geomagnetic_Sensor);
        else if (sensorType == Sensor.TYPE_GYROSCOPE) setTitle(R.string.text_Gyroscope);
        else if (sensorType == Sensor.TYPE_LIGHT) setTitle(R.string.text_Light_Sensor);
        else if (sensorType == Sensor.TYPE_PROXIMITY) setTitle(R.string.text_Proximity_Sensor);
        else if (sensorType == Sensor.TYPE_ORIENTATION) setTitle(R.string.text_Compass);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Sensor sensor = sensorManager.getSensorList(sensorType).get(0);
        if (sensorType == Sensor.TYPE_ORIENTATION)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        else
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ToggleButton toggleButton_Geomagnetic = findViewById(R.id.toggleButton_Geomagnetic);
        ToggleButton toggleButton_Level = findViewById(R.id.toggleButton_Level);
        ProgressBar progressBar_Light = findViewById(R.id.progressBar_Light_sensor);
        ProgressBar progressBar_Level_left = findViewById(R.id.progressBar_Level_left);
        ProgressBar progressBar_Level_right = findViewById(R.id.progressBar_Level_right);
        ProgressBar progressBar_Level_top = findViewById(R.id.progressBar_Level_top);
        ProgressBar progressBar_Level_bottom = findViewById(R.id.progressBar_Level_bottom);
        ImageView imageView_Gyroscope = findViewById(R.id.imageView_Gyroscope);
        ImageView imageView_Compass = findViewById(R.id.imageView_Compass);
        TextView textView_Status = findViewById(R.id.textView_Status);
        TextView textView_Data = findViewById(R.id.textView_Data);
        TextView textView_Axes = findViewById(R.id.textView_Axes);
        StringBuilder stringBuilder = new StringBuilder();

        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            toggleButton_Level.setVisibility(View.VISIBLE);
            if (toggleButton_Level.isChecked()) {
                textView_Axes.setVisibility(View.INVISIBLE);
                textView_Data.setVisibility(View.INVISIBLE);
                progressBar_Level_left.setVisibility(View.VISIBLE);
                progressBar_Level_right.setVisibility(View.VISIBLE);
                progressBar_Level_top.setVisibility(View.VISIBLE);
                progressBar_Level_bottom.setVisibility(View.VISIBLE);
                if (event.values[1] < 0) {
                    progressBar_Level_bottom.setProgress(0);
                    progressBar_Level_top.setProgress(Math.round(event.values[1]) * -1);
                    if (progressBar_Level_top.getProgress() == progressBar_Level_top.getMax())
                        progressBar_Level_top.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#009900")));
                    else progressBar_Level_top.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                }
                else {
                    progressBar_Level_top.setProgress(0);
                    progressBar_Level_bottom.setProgress(Math.round(event.values[1]));
                    if (progressBar_Level_bottom.getProgress() == progressBar_Level_bottom.getMax())
                        progressBar_Level_bottom.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#009900")));
                    else progressBar_Level_bottom.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                }
                if (event.values[0] < 0) {
                    progressBar_Level_right.setProgress(Math.round(event.values[0] * -1));
                    progressBar_Level_left.setProgress(0);
                    if (progressBar_Level_right.getProgress() == progressBar_Level_right.getMax())
                        progressBar_Level_right.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#009900")));
                    else progressBar_Level_right.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                }
                else  {
                    progressBar_Level_right.setProgress(0);
                    progressBar_Level_left.setProgress(Math.round(event.values[0]));
                    if (progressBar_Level_left.getProgress() == progressBar_Level_left.getMax())
                        progressBar_Level_left.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#009900")));
                    else progressBar_Level_left.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                }
                if ((Math.round(event.values[2]) == 10 || Math.round(event.values[2]) == -10)
                        && Math.round(event.values[0]) == 0 && Math.round(event.values[1]) == 0) {
                    progressBar_Level_left.setProgress(10);
                    progressBar_Level_right.setProgress(10);
                    progressBar_Level_top.setProgress(10);
                    progressBar_Level_bottom.setProgress(10);
                    progressBar_Level_left.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#009900")));
                    progressBar_Level_right.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#009900")));
                    progressBar_Level_top.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#009900")));
                    progressBar_Level_bottom.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#009900")));
                }
            }
            else {
                textView_Axes.setVisibility(View.VISIBLE);
                textView_Data.setVisibility(View.VISIBLE);
                progressBar_Level_left.setVisibility(View.INVISIBLE);
                progressBar_Level_right.setVisibility(View.INVISIBLE);
                progressBar_Level_top.setVisibility(View.INVISIBLE);
                progressBar_Level_bottom.setVisibility(View.INVISIBLE);
                stringBuilder.append("Axes\n");
                stringBuilder.append(String.format("%7.4f", event.values[0]));
                stringBuilder.append(" m/s\u00B2\n");
                stringBuilder.append(String.format("%7.4f", event.values[1]));
                stringBuilder.append(" m/s\u00B2\n");
                stringBuilder.append(String.format("%7.4f", event.values[2]));
                stringBuilder.append(" m/s\u00B2");
            }
        }

        else if (sensorType == Sensor.TYPE_PRESSURE) {
            stringBuilder.append("Pressure\n");
            stringBuilder.append(Math.round(event.values[0]));
            stringBuilder.append(" hPa");
        }

        else if (sensorType == Sensor.TYPE_MAGNETIC_FIELD) {
            toggleButton_Geomagnetic.setVisibility(View.VISIBLE);
            textView_Axes.setVisibility(View.VISIBLE);
            stringBuilder.append("Axes\n");
            if (toggleButton_Geomagnetic.isChecked()) {
                stringBuilder.append(String.format("%10.4f", event.values[0]));
                stringBuilder.append(" µT\n");
                stringBuilder.append(String.format("%10.4f", event.values[1]));
                stringBuilder.append(" µT\n");
                stringBuilder.append(String.format("%10.4f", event.values[2]));
                stringBuilder.append(" µT");
            }
            else {
                stringBuilder.append(String.format("%10d", Math.round(event.values[0])));
                stringBuilder.append(" µT\n");
                stringBuilder.append(String.format("%10d", Math.round(event.values[1])));
                stringBuilder.append(" µT\n");
                stringBuilder.append(String.format("%10d", Math.round(event.values[2])));
                stringBuilder.append(" µT");
            }
        }

        else if (sensorType == Sensor.TYPE_GYROSCOPE) {
            textView_Axes.setVisibility(View.VISIBLE);
            stringBuilder.append("Axes\n");
            stringBuilder.append(String.format("%7.2f", event.values[0]));
            stringBuilder.append(" rad/s\n");
            stringBuilder.append(String.format("%7.2f", event.values[1]));
            stringBuilder.append(" rad/s\n");
            stringBuilder.append(String.format("%7.2f", event.values[2]));
            stringBuilder.append(" rad/s");
            if (event.values[0] < 0.005 || event.values[0] > -0.005) {
                imageView_Gyroscope.setVisibility(View.INVISIBLE);
            }

            if (event.values[0] >= 0.3) {
                imageView_Gyroscope.setVisibility(View.VISIBLE);
                stringBuilder.append("\n\nUp");
                imageView_Gyroscope.setImageResource(R.drawable.ic_arrow_up);
            }
            else if (event.values[0] <= -0.3) {
                imageView_Gyroscope.setVisibility(View.VISIBLE);
                stringBuilder.append("\n\nDown");
                imageView_Gyroscope.setImageResource(R.drawable.ic_arrow_down);
            }
            else if (event.values[1] > 0.3) {
                imageView_Gyroscope.setVisibility(View.VISIBLE);
                stringBuilder.append("\n\nLeft");
                imageView_Gyroscope.setImageResource(R.drawable.ic_arrow_left);
            }
            else if (event.values[1] < -0.3) {
                imageView_Gyroscope.setVisibility(View.VISIBLE);
                stringBuilder.append("\n\nRight");
                imageView_Gyroscope.setImageResource(R.drawable.ic_arrow_right);
            }
            else if (event.values[2] > 0.3) {
                imageView_Gyroscope.setVisibility(View.VISIBLE);
                stringBuilder.append("\n\nRotating left");
                imageView_Gyroscope.setImageResource(R.drawable.ic_arrow_left_rotate);
            }
            else if (event.values[2] < -0.3) {
                imageView_Gyroscope.setVisibility(View.VISIBLE);
                stringBuilder.append("\n\nRotating right");
                imageView_Gyroscope.setImageResource(R.drawable.ic_arrow_right_rotate);
            }
        }

        else if (sensorType == Sensor.TYPE_LIGHT) {
            stringBuilder.append("Ambient light level\n");
            stringBuilder.append(Math.round(event.values[0]));
            stringBuilder.append(" lx");
            progressBar_Light.setVisibility(View.VISIBLE);
            progressBar_Light.setMax(600);
            progressBar_Light.setProgress((int) event.values[0]);
        }

        else if (sensorType == Sensor.TYPE_PROXIMITY) {
            stringBuilder.append("Distance\n");
            if (event.values[0] >= event.sensor.getMaximumRange()) stringBuilder.append("Far");
            else stringBuilder.append("Near");
        }

        else if (sensorType == Sensor.TYPE_ORIENTATION) {
            imageView_Compass.setVisibility(View.VISIBLE);
            float degree = Math.round(event.values[0]);
            stringBuilder.append((int) degree + "°");
            RotateAnimation rotateAnimation = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.50f, Animation.RELATIVE_TO_SELF, 0.50f);
            rotateAnimation.setDuration(500);
            rotateAnimation.setRepeatCount(0);
            rotateAnimation.setFillAfter(true);
            imageView_Compass.startAnimation(rotateAnimation);
            currentDegree = -degree;
        }
        textView_Data.setText(stringBuilder);
        textView_Status.setText("Sensor accuracy\n" + (event.accuracy == 3 ? "High" : (event.accuracy == 2 ? "Medium" : "Low")));
        textView_Status.setTextColor(event.accuracy == 3 ? Color.parseColor("#009900") : (event.accuracy == 2 ? Color.parseColor("#999900") : Color.parseColor("#990000")));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
