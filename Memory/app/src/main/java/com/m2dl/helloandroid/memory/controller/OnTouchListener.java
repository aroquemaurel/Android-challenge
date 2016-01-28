package com.m2dl.helloandroid.memory.controller;

import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by florent on 28/01/16.
 */
public class OnTouchListener implements View.OnTouchListener/*, View.OnLongClickListener*/  {
    public enum Player {
        PLAYER_1, PLAYER_2
    }

    private Player currentPlayer;

    private boolean isPlayerMovement;

    /** Timer to check when execute query. */
    private Timer timer;

    private int timePerPlayer;


    public OnTouchListener() {
        currentPlayer = Player.PLAYER_1;
        timePerPlayer = 2000;
        isPlayerMovement = false;
    }

//    @Override
//    public boolean onLongClick(View v) {
//        return false;
//    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!isPlayerMovement) {
            startTimer();
            manageMovements(v, event);
        }
        return false;
    }

    public void manageMovements(View v, MotionEvent event) {
            // TODO :

    }

    /**
     * Next player to play.
     */
    private void nextPlayer() {
        stopTimer();
        switch (currentPlayer) {
            case PLAYER_1:
                currentPlayer = Player.PLAYER_2;
                break;
            case PLAYER_2:
                currentPlayer = Player.PLAYER_1;
                break;
            default:
                currentPlayer = Player.PLAYER_1;
        }
        startTimer();
        timePerPlayer += 500;
    }


    /**
     * Start timer.
     */
    public void startTimer() {
        timer = new Timer();
        isPlayerMovement = true;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                nextPlayer();
            }
        }, timePerPlayer);
    }

    /**
     * Stop timer.
     */
    public void stopTimer() {
        if (timer != null) {
            timer.purge();
            timer.cancel();
            timer = null;
        }
    }

}
