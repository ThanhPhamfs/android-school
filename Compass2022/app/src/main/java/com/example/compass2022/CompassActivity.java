package com.example.compass2022;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class CompassActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor accelermometerSensor = null;
    private Sensor magneticFieldSensor = null;
    ImageView imageViewCompass;

    float[] acceData = new float[3];
    float[] graviData = new float[3];
    float[] rotationMtrix = new float[9];
    float lastAlpha = 0f;
    int DURATION = 10;
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            switch (sensorEvent.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    acceData = sensorEvent.values.clone();
//            data[0] = x, data[1] = y, data[2] = z
//                    float root = (float) (Math.pow(data[0], 2) + Math.pow(data[1], 2) + Math.pow(data[2], 2));
//                    root = (float) (root / (Math.pow(SensorManager.GRAVITY_EARTH, 2)));
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    graviData = sensorEvent.values.clone();
                    break;

            }
            boolean succeed = SensorManager.getRotationMatrix(rotationMtrix, null, acceData, graviData);
            if (succeed){
                float[] orientationValue = new float[3];
//                SensorManager.getOrientation(orientationValue, rotationMtrix);
                SensorManager.getOrientation(rotationMtrix, orientationValue);
                float alpha = (float) Math.toDegrees(-orientationValue[0]);

                RotateAnimation rotateAnimation = new RotateAnimation(lastAlpha, alpha, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(DURATION);
                rotateAnimation.setFillAfter(true);
                imageViewCompass.startAnimation(rotateAnimation);
                lastAlpha = alpha;
            }
//            Toast.makeText(CompassActivity.this, root + "", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass_layout);

//        Get views from layout
        imageViewCompass = findViewById(R.id.imageViewCompass);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelermometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelermometerSensor != null && magneticFieldSensor != null) {
//            3. Register of the sensor with event
            sensorManager.registerListener(sensorEventListener, accelermometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
            sensorManager.registerListener(sensorEventListener, magneticFieldSensor, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(R.string.title);
            dialog.setMessage("Your phone has not the required sensors!");
            dialog.setPositiveButton(R.string.btnOK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.create().show();
        }
    }

    @Override
    protected void onStop() {
        if (accelermometerSensor != null && magneticFieldSensor != null) {
            sensorManager.unregisterListener(sensorEventListener);
        }

        super.onStop();
    }
}