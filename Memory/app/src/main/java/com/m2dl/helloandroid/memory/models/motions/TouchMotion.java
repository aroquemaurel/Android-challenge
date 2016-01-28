package com.m2dl.helloandroid.memory.models.motions;

import android.view.MotionEvent;

/**
 * Created by aroquemaurel on 28/01/16.
 */
public class TouchMotion extends Motion {
    private MotionEvent event;
    private ActionMotion action;

    private float dx = 0;
    private float dy = 0;

    public TouchMotion() {

    }

    public TouchMotion(MotionEvent event, float dx, float dy) {
        loadEvent(event, dx, dy);
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

    public ActionMotion getAction() {
        return action;
    }

    public void setAction(ActionMotion action) {
        this.action = action;
    }

    public MotionEvent getEvent() {
        return event;
    }

    public void setEvent(MotionEvent event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return action == null ? "" : action.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Motion motion = (Motion) o;

        return action == motion.action;

    }

    @Override
    public int hashCode() {
        return action != null ? action.hashCode() : 0;
    }
}
