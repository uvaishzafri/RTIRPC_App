package com.example.yumnaasim.rtirpc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rubengees.introduction.IntroductionBuilder;
import com.rubengees.introduction.Slide;
import com.rubengees.introduction.style.FullscreenStyle;

import java.util.ArrayList;
import java.util.List;

public class IntroSlider extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        new IntroductionBuilder(this).withSlides(generateSlides()).introduceMyself();
        preferences = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        boolean  firstTime=preferences.getBoolean("first", true);
        if(firstTime) {
            editor.putBoolean("first",false);
            editor.commit();
        }
        else
        {
            startActivity(new Intent(IntroSlider.this,SplashScreen.class));
            finish();
        }


    }
    private List<Slide> generateSlides() {
        List<Slide> result = new ArrayList<>();

        result.add(new Slide()
                .withTitle("Welcome!")
                .withDescription(R.string.app_title).
                        withColorResource(R.color.colorPrimaryDark)
                .withImage(R.drawable.logo)
        );

        result.add(new Slide()
                .withTitle("Patient Report")
                .withDescription("Submit the patient report with us")
                .withColorResource(R.color.colorPrimary)
                .withImage(R.drawable.introduction_ic_arrow_next)
        );

        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntroductionBuilder.INTRODUCTION_REQUEST_CODE && resultCode == RESULT_OK) {
           startActivity(new Intent(IntroSlider.this,SplashScreen.class));
            finish();
        }
    }
}

