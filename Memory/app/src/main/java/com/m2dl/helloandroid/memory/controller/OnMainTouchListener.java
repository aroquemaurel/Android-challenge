package com.m2dl.helloandroid.memory.controller;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.m2dl.helloandroid.memory.FullscreenActivity;
import com.m2dl.helloandroid.memory.models.motions.Motion;
import com.m2dl.helloandroid.memory.models.motions.TouchMotion;

/**
 * Created by florent on 28/01/16.
 */
public class OnMainTouchListener implements View.OnTouchListener/*, View.OnLongClickListener*/  {
    private Game game;

    /**
     *
     */
    public OnMainTouchListener(FullscreenActivity context) {
        game = new Game(context);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Motion m = new TouchMotion(event);
        if (!game.isPlayerMovement()) {
            game.startTimer();
        } else {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Log.d("TouchMotion", m.getAction().toString());
                //Toast.makeText(c, "Valeur en cours expected: " + nbMovementExpected + " done : " + nbMovementDone, Toast.LENGTH_SHORT).show();
                game.manageMovements(m);
            }
        }
        return true;
    }

}
