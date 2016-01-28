package com.m2dl.helloandroid.memory.models.motions;

import android.view.MotionEvent;

/**
 * Created by aroquemaurel on 28/01/16.
 * A motion can only be Up, Down, Left, Right, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
 */
public abstract class Motion {
    protected MotionEvent event;
    protected ActionMotion action;


    public abstract void loadEvent(MotionEvent event) ;

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
