package com.m2dl.helloandroid.memory.models;

/**
 * Created by aroquemaurel on 28/01/16.
 */
public enum ActionMotion {
    LEFT, RIGHT, UP, DOWN, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT;

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
            case UP_LEFT:
                return "UP_LEFT";
            case UP_RIGHT:
                return "UP_RIGHT";
            case DOWN_LEFT:
                return "DOWN_LEFT";
            case DOWN_RIGHT:
                return "DOWN_RIGHT";
            default:
                throw new RuntimeException("Unknown error");
        }
    }
}
