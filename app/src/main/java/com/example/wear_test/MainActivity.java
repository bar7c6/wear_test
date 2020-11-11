package com.example.wear_test;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends WearableActivity implements SensorEventListener {

    private TextView mTextView;
    private Chronometer mChronometer;
    private SensorManager sensor_m;
    private List<Sensor> sensors = new ArrayList<>();
    private Sensor mAccelerometer;

//    public MainActivity(){
//        sensor_m = (SensorManager) getSystemService(SENSOR_SERVICE);
//
//        //sensors = sensor_m.getSensorList(Sensor.TYPE_ALL);
//        mAccelerometer = sensor_m.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//
//    }

    @Override
    protected void onResume(){
        super.onResume();

//        for(Sensor sensor:sensors){
//
//            sensor_m.registerListener(this, sensor_m.getDefaultSensor(sensor.getType()),SensorManager.SENSOR_DELAY_NORMAL);
//
//            //mTextView.append(sensor.getName() + "\n");
//
//        }

        sensor_m.registerListener(this, mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);

    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        sensor_m.unregisterListener(this);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView =  findViewById(R.id.text);

        initView();

        // Enables Always-on
        setAmbientEnabled();

        sensor_m = (SensorManager) getSystemService(SENSOR_SERVICE);

        //sensors = sensor_m.getSensorList(Sensor.TYPE_ALL);
        mAccelerometer = sensor_m.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    private void initView() {
        mChronometer = (Chronometer) findViewById(R.id.chronometer_1);

        //setFormat设置用于显示的格式化字符串。
        //替换字符串中第一个“%s”为当前"MM:SS"或 "H:MM:SS"格式的时间显示。
        mChronometer.setFormat("计时：%s");


    }

    // 开始计时
    public void start(View view) {
        mChronometer.start();
    }
    // 停止计时
    public void stop(View view) {
        mChronometer.stop();
    }
    // 重置
    public void reset(View view) {
        //setBase 设置基准时间
        //设置参数base为SystemClock.elapsedRealtime()即表示从当前时间开始重新计时）。
        mChronometer.setBase(SystemClock.elapsedRealtime());
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Float f0 =  sensorEvent.values[0];
        Float f1 =  sensorEvent.values[1];
        Float f2 =  sensorEvent.values[2];

        mTextView.setText(f0.toString() + "\n" + f1.toString() +"\n" + f2.toString());

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
