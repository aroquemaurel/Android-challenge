package com.m2dl.helloandroid.memory.models.motions;

import android.view.MotionEvent;

/**
 * Created by aroquemaurel on 28/01/16.
 */
public class TouchMotion extends Motion {

    private float dx = 0;
    private float dy = 0;

    public TouchMotion() {

    }

    public TouchMotion(MotionEvent event) {
        loadEvent(event);
    }

    public void loadEvent(MotionEvent event) {
        // Use dx and dy to determine the direction
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) action = ActionMotion.RIGHT;//Log.d("toto", "right");
            else action = ActionMotion.LEFT;//Log.d("toto", "left");
        } else {
            if (dy > 0) action=ActionMotion.DOWN;//Log.d("toto", "down");
            else action=ActionMotion.UP;//Log.d("toto", "up");
        }

        // , float dx, float dy

    }


}
