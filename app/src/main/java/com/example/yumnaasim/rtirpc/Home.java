package com.example.yumnaasim.rtirpc;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import model.CustomAdapter;

public class Home extends AppCompatActivity {

    public static String[] nameList = {
            "About",
            "Submit Report",
            "Analyze Result",
            "Preview Report"
    };
    public static int[] images = {
            R.drawable.about,
            R.drawable.submit_report,
            R.drawable.report,
            R.drawable.preview_report
           };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setTitle("Home");
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new CustomAdapter(this, nameList,images));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
//                        about app
                        Intent intentAbout = new Intent(Home.this, About.class);
                        startActivity(intentAbout);
                        break;
                    case 1:
//
                        Intent intent = new Intent(Home.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
//
                        Intent intent1 = new Intent(Home.this, Report.class);
                        startActivity(intent1);
                        break;

                    case 3:
//
                        Intent intent2 = new Intent(Home.this, Report.class);
                        startActivity(intent2);
                        break;
                }
            }
        });
    }
}
