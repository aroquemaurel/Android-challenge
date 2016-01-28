package com.m2dl.helloandroid.memory.models.motions;

import android.hardware.SensorEvent;

/**
 * Created by aroquemaurel on 28/01/16.
 */
public class GyroMotion extends Motion<SensorEvent> {
    public GyroMotion(SensorEvent event) {
        super(event);
    }

    public GyroMotion() {

    }

    @Override
    public void loadEvent(SensorEvent event) {
        
    }
}
