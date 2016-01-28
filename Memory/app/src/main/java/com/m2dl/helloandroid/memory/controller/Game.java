package com.m2dl.helloandroid.memory.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.m2dl.helloandroid.memory.FullscreenActivity;
import com.m2dl.helloandroid.memory.R;
import com.m2dl.helloandroid.memory.models.motions.Motion;
import com.m2dl.helloandroid.memory.models.motions.MotionList;

import java.util.Timer;

/**
 * Created by aroquemaurel on 28/01/16.
 */
public class Game {

    public boolean isPlayerMovement() {
        return isPlayerMovement;
    }

    public Context getContext() {
        return c;
    }

    public enum Player {
        PLAYER_1, PLAYER_2
    }

    private Player currentPlayer;
    private int nbMovementExpected = 1;
    private int nbMovementDone = 0;

    private final int TIMER_INCREMENT = 1000;
    private FullscreenActivity c;

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

    public Game(FullscreenActivity context) {
        currentPlayer = Player.PLAYER_1;
        timePerPlayer = 5000;
        isPlayerMovement = false;
        nbLines = 0;
        c = context;
        currentPlayer = Player.PLAYER_1;
        timePerPlayer = 5000;
        isPlayerMovement = false;


        tvCurrentPlayer = (TextView) c.findViewById(R.id.tv_current_player);
        tvFormsCounter = (TextView) c.findViewById(R.id.tv_forms_counter);
        tvForms = (TextView) c.findViewById(R.id.tv_forms);
        tvTimer = (TextView) c.findViewById(R.id.tv_timer);
        chrono = (Chronometer) c.findViewById(R.id.chronometer);

        nbLines = 0;

    }

    public void doMovement(Motion m) {
    }

    public void manageMovements(Motion m, View v) {
        MotionList listePlayer1 = c.getListePlayer();
        // Si joueur 1 on ajoute dans sa liste
        if(nbMovementDone +1 > nbMovementExpected){
            Toast.makeText(c, "calm your tits", Toast.LENGTH_SHORT).show();
        } else {
            if (currentPlayer.equals(Player.PLAYER_1)) {
                //Cas 0 on ajoute et c'est tout
                if (listePlayer1.size() == 0) {
                    listePlayer1.add(m);
                    nbMovementDone++;
                    //nextPlayer();
                    //Toast.makeText(c, "TEST INIT", Toast.LENGTH_SHORT).show();
                } else {
                    //On regarde si on doit tester le mouvement ou si c'est un supplémentaire
                    if (nbMovementDone + 1 < nbMovementExpected) {
                        //Si le joueur fait le bon mouvement

                        if (m.getAction().equals(listePlayer1.get(nbMovementDone).getAction())) {
                            Toast.makeText(c, "Bon mouvement réalisé", Toast.LENGTH_SHORT).show();
                            nbMovementDone++;
                        } else {
                            Toast.makeText(c, "mauvais mouvememnt, le joueur 1 a perdu", Toast.LENGTH_SHORT).show();
                            c.showEndPopup(2);


                        }
                    } else {
                        if (nbMovementDone + 1 == nbMovementExpected) {
                            listePlayer1.add(m);
                            nbMovementDone++;
                            //nextPlayer();
                            Toast.makeText(c, "Mouvement ajouté en position " + nbMovementDone, Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            } else {
                //Cas 0 on ajoute et c'est tout
                //On regarde si on doit tester le mouvement ou si c'est un supplémentaire
                if (nbMovementDone + 1 < nbMovementExpected) {
                    //Si le joueur fait le bon mouvement
                    //Toast.makeText(c, "Taille de la liste J1 : "+listPlayer1.size(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "INDICE " + nbMovementDone);
                    Log.d("TAG", "Object : " + m.getAction().toString());
                    Toast.makeText(c, "m : "+m.getAction().toString()+ " Expected : "+listePlayer1.get(nbMovementDone).getAction().toString(), Toast.LENGTH_SHORT).show();
                    if (m.getAction().equals(listePlayer1.get(nbMovementDone).getAction())) {
                        Toast.makeText(c, "Bon mouvement réalisé", Toast.LENGTH_SHORT).show();
                        nbMovementDone++;
                    } else {
                        Toast.makeText(c, "mauvais mouvememnt, le joueur 2 a perdu", Toast.LENGTH_SHORT).show();
                        c.showEndPopup(1);
                    }
                } else {
                    if (nbMovementDone + 1 == nbMovementExpected) {
                        listePlayer1.add(m);
                        nbMovementDone++;
                        //nextPlayer();
                    }

                }


            }
        }

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
                nbMovementExpected++;
                nbMovementDone = 0;
                refreshUI(false);
                break;
            case PLAYER_2:
                currentPlayer = Player.PLAYER_1;
                nbMovementExpected++;
                nbMovementDone = 0;
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

        new CountDownTimer(timePerPlayer, TIMER_INCREMENT) {

            public void onTick(long millisUntilFinished) {
                chrono.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                chrono.setText("0");
                MotionList listePlayer1 = c.getListePlayer();
                if(nbMovementExpected == listePlayer1.size()){
                    nextPlayer();
                } else {
                    if(currentPlayer == Player.PLAYER_1){
                        c.showEndPopup(2);
                    } else {
                        c.showEndPopup(1);
                    }

                }


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
