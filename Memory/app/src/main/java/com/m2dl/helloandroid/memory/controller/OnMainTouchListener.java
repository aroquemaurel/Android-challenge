package com.m2dl.helloandroid.memory.controller;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.m2dl.helloandroid.memory.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by florent on 28/01/16.
 */
public class OnMainTouchListener implements View.OnTouchListener/*, View.OnLongClickListener*/  {
    public enum Player {
        PLAYER_1, PLAYER_2
    }

    private Player currentPlayer;

    private final int TIMER_INCREMENT = 500;

    private Activity c;

    private boolean isPlayerMovement;
    private int nbLines;

    /** Timer to check when execute query. */
    private Timer timer;

    private int timePerPlayer;

    //-----------------------------------
    // IHM
    //------------------------------------
    private TextView tvCurrentPlayer;
    private TextView tvFormsCounter;
    private TextView tvTimer;
    private TextView tvForms;
    private Chronometer chrono;



    /**
     *
     */
    public OnMainTouchListener(Activity context) {
        c = context;
        currentPlayer = Player.PLAYER_1;
        timePerPlayer = 2000;
        isPlayerMovement = false;

        tvCurrentPlayer = (TextView) c.findViewById(R.id.tv_current_player);
        tvFormsCounter = (TextView) c.findViewById(R.id.tv_forms_counter);
        tvForms = (TextView) c.findViewById(R.id.tv_forms);
        tvTimer = (TextView) c.findViewById(R.id.tv_timer);
        chrono = (Chronometer) c.findViewById(R.id.chronometer);

        nbLines = 0;

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
    @TargetApi(Build.VERSION_CODES.M)
    private void nextPlayer() {
        //stopTimer();
        timePerPlayer += TIMER_INCREMENT;
        switch (currentPlayer) {
            case PLAYER_1:
                currentPlayer = Player.PLAYER_2;
                refreshUI(false);
                break;
            case PLAYER_2:
                currentPlayer = Player.PLAYER_1;
                refreshUI(true);
                break;
            default:
                currentPlayer = Player.PLAYER_1;
        }
        startTimer();
    }

    public void refreshUI(final boolean isPlayer1) {
        final int color;
        if (isPlayer1) {
            tvCurrentPlayer.setText(c.getResources().getString(R.string.player_1));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                color = c.getResources().getColor(R.color.colorPlayer1, c.getTheme());
            } else {
                color = c.getResources().getColor(R.color.colorPlayer1);
            }
        } else {
            tvCurrentPlayer.setText(c.getResources().getString(R.string.player_2));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                color = c.getResources().getColor(R.color.colorPlayer2, c.getTheme());
            } else {
                color = c.getResources().getColor(R.color.colorPlayer2);
            }
        }

        c.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvCurrentPlayer.setTextColor(color);
                tvForms.setTextColor(color);
                tvTimer.setTextColor(color);
                chrono.setTextColor(color);
                tvFormsCounter.setTextColor(color);
                tvFormsCounter.setText(String.valueOf(nbLines));
            }
        });

    }



    /**
     * Start timer.
     */
    public void startTimer() {
        timer = new Timer();
        isPlayerMovement = true;
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                nextPlayer();
//            }
//        }, timePerPlayer);
        new CountDownTimer(timePerPlayer, TIMER_INCREMENT) {

            public void onTick(long millisUntilFinished) {
                chrono.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                chrono.setText("0");
                nextPlayer();

            }
        }.start();
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
