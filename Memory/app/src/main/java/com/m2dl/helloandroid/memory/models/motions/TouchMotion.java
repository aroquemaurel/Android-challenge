package com.m2dl.helloandroid.memory.models.motions;

import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by aroquemaurel on 28/01/16.
 */
public class TouchMotion extends Motion {
    public TouchMotion() {

    }

    public TouchMotion(MotionEvent event) {
        loadEvent(event);
    }
    private static float x1;
    private static float y1;

    public void loadEvent(MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float x2 = event.getX();
                float y2 = event.getY();
                float dx = x2-x1;
                float dy = y2-y1;

                // Use dx and dy to determine the direction
                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx > 0) action = ActionMotion.RIGHT;
                    else action = ActionMotion.LEFT;
                } else {
                    if (dy > 0) action=ActionMotion.DOWN;
                    else action=ActionMotion.UP;
                }
        }
    }


}
