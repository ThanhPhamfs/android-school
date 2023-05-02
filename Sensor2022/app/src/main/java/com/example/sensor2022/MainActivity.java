package com.example.sensor2022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sensor2022.Admin.MyAdmin;
import com.example.sensor2022.Models.SensorInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
    * XIN QUYỀN ADMIN
    * 1. Tạo MyAdmin extends DeviceAdminReceiver
    * 2. Tạo policies, file type admin
    * 3. Khai báo receiver on manifest:
    *           <receiver android:name=".Admin.MyAdmin" android:permission="android.permission.BIND_DEVICE_ADMIN"
                android:exported="true">
                <intent-filter>
                    <action android:name="android.app.action.DEVICE_ADMIN_ENABLE"/>

                </intent-filter>
                <meta-data
                    android:name="android.app.device_admin"
                    android:resource="@xml/poliies"
                    />
            </receiver>
    * 4. Khai báo trước setContentView:
    *           devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
    *   componentName = new ComponentName(this, MyAdmin.class);
    */ ListView lvSensor;
    TextView tvSensor;
    ArrayList<SensorInfo> list = new ArrayList<>();
    private SensorManager sensorManager;
    private Sensor sensor = null;
    //    For admin permission
    private DevicePolicyManager devicePolicyManager;
    private ComponentName componentName;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] data = sensorEvent.values;
//            data[0] = x, data[1] = y, data[2] = z
            float root = (float) (Math.pow(data[0], 2) + Math.pow(data[1], 2) + Math.pow(data[2], 2));
            root = (float) (root / (Math.pow(SensorManager.GRAVITY_EARTH, 2)));
            tvSensor.setText(root + "");
            if (root >= 1) {
                boolean active = devicePolicyManager.isAdminActive(componentName);
                if (active) {
                    //Lock the screen
                    devicePolicyManager.lockNow();
                } else {
                    Toast.makeText(MainActivity.this, "No admin permission!", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    //2. Declare the sensor event listener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//For the admin permission
        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, MyAdmin.class);
        setContentView(R.layout.activity_main);
//For the admin permission
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        startActivity(intent);

        lvSensor = findViewById(R.id.lvSensor);
        tvSensor = findViewById(R.id.tvSensor);

        tvSensor.setText("Sensor 1");
//        list.add(new SensorInfo("Sensor 1", "nsx1"));
//        list.add(new SensorInfo("Sensor 1", "nsx1"));
//        List<Sensor> sensors = ((SensorManager) getSystemService(Context.SENSOR_SERVICE)).getSensorList(Sensor.TYPE_ALL);
//        for (Sensor sensor :
//                sensors) {
//            list.add(new SensorInfo(sensor.getName(), sensor.getVendor()));
//        }

//        Initialization
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            list.add(new SensorInfo(sensor.getName(), sensor.getVendor()));
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lvSensor.setAdapter(arrayAdapter);


//        Get the sensor to process
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensor != null) {
//            3. Register of the sensor with event
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            Toast.makeText(this, "This phone has not ACCELEROMETER sensor", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        if (sensor != null) {
            sensorManager.unregisterListener(sensorEventListener);
        }
        super.onStop();
    }
}