package com.m2dl.helloandroid.memory.util;

import android.app.Activity;
import android.app.usage.UsageEvents;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.m2dl.helloandroid.memory.R;
import com.m2dl.helloandroid.memory.models.motions.ActionMotion;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by florent on 28/01/16.
 */
public class ManagerSensorImage {

    private Activity c;
    private ImageView ivUp;
    private ImageView ivLeft;
    private ImageView ivRight;
    private ImageView ivDown;
    private ImageView ivUpCompass;
    private ImageView ivLeftCompass;
    private ImageView ivRightCompass;
    private ImageView ivDownCompass;

    private Drawable arrow_up;
    private Drawable arrow_left;
    private Drawable arrow_right;
    private Drawable arrow_down;
    private Drawable compass;


    public ManagerSensorImage(Activity activity) {
        c = activity;
        ivUp = (ImageView) c.findViewById(R.id.iv_up_zone);
        ivLeft = (ImageView) c.findViewById(R.id.iv_left_zone);
        ivRight = (ImageView) c.findViewById(R.id.iv_right_zone);
        ivDown = (ImageView) c.findViewById(R.id.iv_down_zone);
        ivUpCompass = (ImageView) c.findViewById(R.id.iv_up_compass_zone);
        ivLeftCompass = (ImageView) c.findViewById(R.id.iv_left_compass_zone);
        ivRightCompass = (ImageView) c.findViewById(R.id.iv_right_compass_zone);
        ivDownCompass = (ImageView) c.findViewById(R.id.iv_down_compass_zone);

        ivUp.setVisibility(View.INVISIBLE);
        ivLeft.setVisibility(View.INVISIBLE);
        ivRight.setVisibility(View.INVISIBLE);
        ivDown.setVisibility(View.INVISIBLE);
        ivUpCompass.setVisibility(View.INVISIBLE);
        ivLeftCompass.setVisibility(View.INVISIBLE);
        ivRightCompass.setVisibility(View.INVISIBLE);
        ivDownCompass.setVisibility(View.INVISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            arrow_up = c.getResources().getDrawable(R.mipmap.ic_arrow_up, c.getTheme());
            arrow_left = c.getResources().getDrawable(R.mipmap.ic_arrow_left, c.getTheme());
            arrow_right = c.getResources().getDrawable(R.mipmap.ic_arrow_right, c.getTheme());
            arrow_down = c.getResources().getDrawable(R.mipmap.ic_arrow_down, c.getTheme());
            compass = c.getResources().getDrawable(R.mipmap.ic_compass, c.getTheme());
        } else {
            arrow_up = c.getResources().getDrawable(R.mipmap.ic_arrow_up);
            arrow_left = c.getResources().getDrawable(R.mipmap.ic_arrow_left);
            arrow_right = c.getResources().getDrawable(R.mipmap.ic_arrow_right);
            arrow_down = c.getResources().getDrawable(R.mipmap.ic_arrow_down);
            compass = c.getResources().getDrawable(R.mipmap.ic_compass);
        }

        ivUp.setImageDrawable(arrow_up);
        ivLeft.setImageDrawable(arrow_left);
        ivRight.setImageDrawable(arrow_right);
        ivDown.setImageDrawable(arrow_down);
        ivUpCompass.setImageDrawable(compass);
        ivLeftCompass.setImageDrawable(compass);
        ivRightCompass.setImageDrawable(compass);
        ivDownCompass.setImageDrawable(compass);

    }

    public void display(ActionMotion action, final boolean isGyro) {
        switch (action) {
            case LEFT:
                if (isGyro) {
                    setVisible(ivLeftCompass);
                } else {
                    setVisible(ivLeft);
                }
                break;
            case RIGHT:
                if (isGyro) {
                    setVisible(ivRightCompass);
                } else {
                    setVisible(ivRight);
                }
                break;
            case UP:
                if (isGyro) {
                    setVisible(ivUpCompass);
                } else {
                    setVisible(ivUp);
                }
                break;
            case DOWN:
                if (isGyro) {
                    setVisible(ivDownCompass);
                } else {
                    setVisible(ivDown);
                }
                break;
        }
    }

    public void setVisible(final ImageView ivToVisible) {
        Timer t = new Timer();
        ivToVisible.setVisibility(View.VISIBLE);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                ivToVisible.setVisibility(View.INVISIBLE);
            }
        }, 1000);


    }
}
