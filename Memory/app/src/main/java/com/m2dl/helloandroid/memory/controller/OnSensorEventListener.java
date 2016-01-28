package com.m2dl.helloandroid.memory.controller;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.m2dl.helloandroid.memory.models.motions.ActionMotion;

/**
 * Created by florent on 28/01/16.
 */
public class OnSensorEventListener implements SensorEventListener {

    private final int DELTA = 100;
    private Float verticalValue;
    private Float horizontalValue;

    private float initVerticalValue;
    private float initHorizontalValue;

    public OnSensorEventListener() {
        verticalValue = null;
        horizontalValue = null;
        initVerticalValue = 0f;
        initHorizontalValue = 0f;
    }
    private static int toto = 0;
    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensor = event.sensor.getType();
        float [] values = event.values;

        synchronized (this) {
                if (sensor == Sensor.TYPE_MAGNETIC_FIELD) {
                    if (verticalValue == null || horizontalValue == null) {
                        initVerticalValue = values[0];
                        initHorizontalValue = values[2];
                        verticalValue = values[0];
                        horizontalValue = values[2];
                    } else {
                        verticalValue += (initVerticalValue-values[0]);
                        horizontalValue += (initHorizontalValue-values[2]);
                    }

                    ActionMotion action = detectMotionSensor();
                    if (action != null) {
                        Log.d("ActionMotion", action.toString()
                                + " : " + verticalValue + "(" + initVerticalValue + ") "
                                + horizontalValue + "(" + initHorizontalValue + ")");

                        horizontalValue = null;
                        verticalValue = null;
                    } else {

                        // Euuuh. I don't know... Any Idea ? Fuck off.
                    }
                }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d("ACCURACY","" + accuracy);
    }

    public ActionMotion detectMotionSensor() {
        float absHoriz = (initHorizontalValue-horizontalValue);
        float absVertic = (initVerticalValue-verticalValue);
        Log.d("COMPUTE","h: " + absHoriz + " v:" + absVertic);
        if(absHoriz >= DELTA) {
            // youhou, right value
            return ActionMotion.RIGHT;
        } else if(absHoriz < -DELTA) {
            // Yatta left value
            return ActionMotion.LEFT;
        } else if (absVertic >= DELTA) {
            // yihaaa  top value
            return ActionMotion.UP;
        } else if(absVertic < -DELTA) {
            // yeaaah  bottom value
            return ActionMotion.DOWN;
        }

        return null;
    }
}
