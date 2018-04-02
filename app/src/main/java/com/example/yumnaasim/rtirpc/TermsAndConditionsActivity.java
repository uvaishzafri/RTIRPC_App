package com.example.yumnaasim.rtirpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

public class TermsAndConditionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        getSupportActionBar()
                .setTitle(Html.fromHtml("<font color=\"#FFFFFF\">" + "Terms & Agreement" + "</font>"));

        Button button = (Button) findViewById(R.id.btnOk);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TermsAndConditionsActivity.this,NavDrawerActivity.class));
                finish();
            }
        });
    }
}
