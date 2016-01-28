package com.m2dl.helloandroid.memory.models.motions;

import android.view.MotionEvent;

/**
 * Created by aroquemaurel on 28/01/16.
 * A motion can only be Up, Down, Left, Right, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
 */
public abstract class Motion {
    public abstract void loadEvent(MotionEvent event) ;
}
