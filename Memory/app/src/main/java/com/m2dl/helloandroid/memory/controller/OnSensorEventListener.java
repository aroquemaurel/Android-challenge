package com.m2dl.helloandroid.memory.controller;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.Toast;

import com.m2dl.helloandroid.memory.FullscreenActivity;
import com.m2dl.helloandroid.memory.models.motions.ActionMotion;
import com.m2dl.helloandroid.memory.models.motions.GyroMotion;
import com.m2dl.helloandroid.memory.models.motions.Motion;
import com.m2dl.helloandroid.memory.models.motions.TouchMotion;

/**
 * Created by florent on 28/01/16.
 */
public class OnSensorEventListener implements SensorEventListener {
    private Game game;

    private final int DELTA = 30;
    private Float verticalValue;
    private Float horizontalValue;

    private float initVerticalValue;
    private float initHorizontalValue;

    private ActionMotion lastMove;

    private boolean newMovement = true;

    public OnSensorEventListener(FullscreenActivity context) {
        verticalValue = null;
        horizontalValue = null;
        initVerticalValue = 0f;
        initHorizontalValue = 0f;
        lastMove = null;
        game = new Game(context);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensor = event.sensor.getType();
        float [] values = event.values;

        synchronized (this) {
            // if (sensor == Sensor.TYPE_MAGNETIC_FIELD) {
            if (sensor == Sensor.TYPE_ACCELEROMETER) {
                    if (verticalValue == null || horizontalValue == null) {
                        initVerticalValue = values[0];
                        initHorizontalValue = values[1];
                        verticalValue = values[0];
                        horizontalValue = values[1];
                    } else {
                        verticalValue += (initVerticalValue-values[0]);
                        horizontalValue += (initHorizontalValue-values[1]);
                    }
                    ActionMotion action = detectMotionSensor();

                    if (action != null) {
                        if(lastMove == null) {
                            Motion m = new GyroMotion(event);
                            m.setAction(action);
                            Toast.makeText(game.getContext(), "Motion "+m.toString(), Toast.LENGTH_SHORT).show();
                            horizontalValue = null;
                            verticalValue = null;
                            newMovement = false;
                            lastMove = action;
                            if (!game.isPlayerMovement()) {
                                game.startTimer();
                            } else {
                                game.manageMovements(m, null);
                            }
                        } else {
                            if(values[0] >= -5 && values[0] < 5 && values[1] >= -5 && values[1] < 5) {
                                lastMove = null;
                                initVerticalValue = values[0];
                                initHorizontalValue = values[1];
                                verticalValue = values[0];
                                horizontalValue = values[1];
                            }
                        }
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

        if(absHoriz >= DELTA) {
            return ActionMotion.UP;
        } else if(absHoriz < -1*DELTA) {
            return ActionMotion.DOWN;
        } else if (absVertic >= DELTA) {
            return ActionMotion.LEFT;
        } else if(absVertic < -1*DELTA) {
            return ActionMotion.RIGHT;
        }

        return null;
    }
}
