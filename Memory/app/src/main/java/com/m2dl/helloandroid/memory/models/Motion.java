package com.m2dl.helloandroid.memory.models;

import android.view.MotionEvent;

/**
 * Created by aroquemaurel on 28/01/16.
 * A motion can only be Up, Down, Left, Right, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
 */
public class Motion  {
    private MotionEvent event;
    private ActionMotion action;

    private float x1 = 0, x2 = 0, y1 = 0, y2 = 0, dx = 0, dy = 0;

    public Motion() {

    }

    public Motion(MotionEvent event) {
        loadEvent(event);
    }

    public void loadEvent(MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                dx = x2 - x1;
                dy = y2 - y1;

                // Use dx and dy to determine the direction
                if (Math.abs(dx) > Math.abs(dy)) {
                    action = (dx > 0) ? ActionMotion.RIGHT : ActionMotion.LEFT;
                } else {
                    action = (dx > 0) ? ActionMotion.DOWN : ActionMotion.UP;
                }
        }
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
        return action.toString();
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
