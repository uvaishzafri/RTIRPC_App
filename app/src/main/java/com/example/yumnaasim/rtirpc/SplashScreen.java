package com.example.yumnaasim.rtirpc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rubengees.introduction.IntroductionBuilder;
import com.rubengees.introduction.Slide;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        TextView tv = (TextView) findViewById(R.id.text);
       /* TextView tv1 = (TextView) findViewById(R.id.text1);*/
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/nabila.otf");
        tv.setTypeface(face);
   /*     tv1.setTypeface(face);*/
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(2000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        thread.start();
    }

}
