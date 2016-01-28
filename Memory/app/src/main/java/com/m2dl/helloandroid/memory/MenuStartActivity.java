package com.m2dl.helloandroid.memory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuStartActivity extends Activity {
    private Button buttonEasy;
    private Button buttonNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_start);
        buttonEasy = (Button) findViewById(R.id.buttonEasy);
        buttonNormal = (Button) findViewById(R.id.buttonNormal);
        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStartEasy = new Intent(MenuStartActivity.this,FullscreenActivity.class);
                Bundle b = new Bundle();
                b.putBoolean("isEasy", true);
                intentStartEasy.putExtras(b);
                startActivity(intentStartEasy);
            }
        });

        buttonNormal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentStartNormal = new Intent(MenuStartActivity.this,FullscreenActivity.class);
                Bundle b = new Bundle();
                b.putBoolean("isEasy", false);
                intentStartNormal.putExtras(b);
                startActivity(intentStartNormal);
            }
        });


    }
}
