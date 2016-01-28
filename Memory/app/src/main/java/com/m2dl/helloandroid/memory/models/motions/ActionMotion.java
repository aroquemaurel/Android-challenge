package com.m2dl.helloandroid.memory.models.motions;

/**
 * Created by aroquemaurel on 28/01/16.
 */
public enum ActionMotion {
    LEFT, RIGHT, UP, DOWN;

    @Override
    public String toString() {
        switch(this) {
            case LEFT:
                return "LEFT";
            case RIGHT:
                return "RIGHT";
            case UP:
                return "UP";
            case DOWN:
                return "DOWN";
            default:
                throw new RuntimeException("Unknown error");
        }
    }
}
